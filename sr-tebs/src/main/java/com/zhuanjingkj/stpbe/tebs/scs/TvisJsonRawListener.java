package com.zhuanjingkj.stpbe.tebs.scs;

import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @KafkaListener(id = "TvisJsonRawListener", topics = "tvis")
    public void listen(String json) {
        logger.info("TvisJsonRawListener 监听到消息:" + json + "!");
        JSONObject jo = JSONObject.parseObject(json);
        String relativeImageFile = jo.getString("ImageUrl");
        String imageFile = AppConst.VIDEO_FRAME_IMG_BASE_DIR + relativeImageFile.substring(2);
        Optional<String> imgRst = IpfsClient.uploadFile(imageFile);
        final StringBuilder imageHash = new StringBuilder();
        imgRst.ifPresent((str) -> {
            JSONObject imgJo = JSONObject.parseObject(str);
            imageHash.append(imgJo.getString("Hash"));
        });
        System.out.println("    @@@@@ imageHash: " + imageHash.toString() + "!");
        // 生成临时JSON文件，上传到IPFS得到jsonHash
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        Random rand = new Random();
        String jf = "json_" + System.currentTimeMillis() + "_" + rand.nextInt() + ".json";
        try {
            fos = new FileOutputStream(new File(jf));
            bw = new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            bw.write(json);
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
            JSONObject jsonJo = JSONObject.parseObject(jsonStr);
            jsonHash.append(jsonJo.getString("Hash"));
        });
        System.out.println("    @@@@@ jsonHash: " + jsonHash.toString() + "!");
        logger.info("    raw: 上传到IPFS...");
        // 获取imageHash、cameraId、streamId、pts，将其存入mysql数据库中
        logger.info("    raw: 保存到数据库中...");
    }
}
