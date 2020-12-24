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
import org.springframework.kafka.annotation.KafkaListener;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Random;

/**
 * 将JSON文件保存到IPFS系统中去，将信息保存到数据库中
 */
public class TvisJsonRawListener {
    public final static Logger logger = LoggerFactory.getLogger(TvisJsonRawListener.class);
    private static boolean isInitialized = false;
    @Autowired
    private TvisJsonMapper tvisJsonMapper;

    private void initialize() {
        if (isInitialized) {
            return ;
        }
        // 生成当前数据表
        String tvisJsonTblName = tvisJsonMapper.getLatesTvisJsonTblName();
        String[] arrs = tvisJsonTblName.split("_");
        long idx = Long.parseLong(arrs[arrs.length - 1]);
        AppRegistry.tvisJsonTblName = AppConst.TVIS_JSON_TBL_PREFIX + String.format("%08d", idx+1);
        AppRegistry.tvisJsonTblRecs = 0;
        tvisJsonMapper.createTvisJsonTbl(AppRegistry.tvisJsonTblName);

        TvisJsonVO vo = new TvisJsonVO(AppRegistry.tvisJsonTblName, 1, "2020-12-24", 101, 201, 301, "ish", "jsh");
        tvisJsonMapper.insertTvisJson(vo);

        isInitialized = true;
    }

    @KafkaListener(id = "TvisJsonRawListener", topics = "tvis")
    public void listen(String json) {
        if (!isInitialized) {
            synchronized (logger) {
                initialize();
            }
        }
        logger.info("TvisJsonRawListener 监听到消息:" + json + "!");
        JSONObject jo = JSONObject.parseObject(json);
        String relativeImageFile = jo.getJSONObject("json").getString("ImageUrl");
        String imageFile = AppConst.VIDEO_FRAME_IMG_BASE_DIR + relativeImageFile.substring(2);
        Optional<String> imgRst = IpfsClient.uploadFile(imageFile);
        final StringBuilder imageHash = new StringBuilder();
        imgRst.ifPresent((str) -> {
            imageHash.append(str);
        });
        System.out.println("    @@@@@ imageHash: " + imageHash.toString() + "!");
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
        System.out.println("    @@@@@ jsonHash: " + jsonHash.toString() + "!");
        logger.info("    raw: 上传到IPFS...");
        // 获取imageHash、cameraId、streamId、pts，将其存入mysql数据库中
        logger.info("    raw: 保存到数据库中...");
    }
}
