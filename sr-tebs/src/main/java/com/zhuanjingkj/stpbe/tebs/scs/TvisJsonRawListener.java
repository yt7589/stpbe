package com.zhuanjingkj.stpbe.tebs.scs;

import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.tebs.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.tebs.vo.TvisJsonVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

/**
 * 将JSON文件保存到IPFS系统中去，将信息保存到数据库中
 */
public class TvisJsonRawListener {
    public final static Logger logger = LoggerFactory.getLogger(TvisJsonRawListener.class);
    private static boolean isInitialized = false;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TvisJsonMapper tvisJsonMapper;

    private void initialize() {
        if (isInitialized) {
            return ;
        }
        rotateTvisJsonTbl();
        isInitialized = true;
    }

    private void rotateTvisJsonTbl() {
        // 生成当前数据表
        String tvisJsonTblName = tvisJsonMapper.getLatesTvisJsonTblName();
        String[] arrs = tvisJsonTblName.split("_");
        long idx = Long.parseLong(arrs[arrs.length - 1]);
        AppRegistry.tvisJsonTblName = AppConst.TVIS_JSON_TBL_PREFIX + String.format("%08d", idx+1);
        AppRegistry.tvisJsonTblRecs = 0;
        tvisJsonMapper.createTvisJsonTbl(AppRegistry.tvisJsonTblName);
    }

    @KafkaListener(id = "TvisJsonRawListener", topics = "tvis")
    public void listen(String json) {
        if (!isInitialized) {
            synchronized (logger) {
                initialize();
            }
        }
        JSONObject jo = JSONObject.parseObject(json);
        String relativeImageFile = jo.getJSONObject("json").getString("ImageUrl");
        String imageFile = AppConst.VIDEO_FRAME_IMG_BASE_DIR + relativeImageFile.substring(2);
        Optional<String> imgRst = IpfsClient.uploadFile(imageFile);
        final StringBuilder imageHash = new StringBuilder();
        imgRst.ifPresent((str) -> {
            imageHash.append(str);
        });
        // 生成临时JSON文件，上传到IPFS得到jsonHash
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        Random rand = new Random();
        String jf = AppConst.JSON_TMP_BASE_DIR + "json_" + System.currentTimeMillis() + "_" + rand.nextInt() + ".json";
        try {
            fos = new FileOutputStream(new File(jf));
            bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            bw.write(json);
            bw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        final StringBuilder jsonHash = new StringBuilder();
        Optional<String> jsonRst = IpfsClient.uploadFile(jf);
        jsonRst.ifPresent((jsonStr)->{
            jsonHash.append(jsonStr);
        });
        // 获取imageHash、cameraId、streamId、pts，将其存入mysql数据库中

        long tvisJsonId = 0;
        if (jo.containsKey("tvisJsonId")) {
            tvisJsonId = jo.getLong("tvisJsonId");
        } else {
            tvisJsonId = redisTemplate.opsForValue().increment(AppConst.TVIS_JSON_TBL_ID_KEY);
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String occurTime = df.format(new Date());
        String cameraIdStr = jo.getString("cameraId");
        long cameraId = Long.parseLong(cameraIdStr);
        JSONObject rstJo = jo.getJSONObject("json");
        String streamIdStr = rstJo.getString("StreamID");
        long streamId = Long.parseLong(streamIdStr);
        String ptsStr = rstJo.getString("TimeStamp");
        long pts = Long.parseLong(ptsStr);
        TvisJsonVO vo = new TvisJsonVO(AppRegistry.tvisJsonTblName, tvisJsonId, occurTime,
                cameraId, streamId, pts, imageHash.toString(), jsonHash.toString());
        tvisJsonMapper.insertTvisJson(vo);
        AppRegistry.tvisJsonTblRecs++;
        if (AppRegistry.tvisJsonTblRecs >= AppConst.TVIS_JSON_TBL_MAX_RECS) {
            rotateTvisJsonTbl();
        }
    }
}
