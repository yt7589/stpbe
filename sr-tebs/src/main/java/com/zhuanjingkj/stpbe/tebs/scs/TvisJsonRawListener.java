package com.zhuanjingkj.stpbe.tebs.scs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * 将JSON文件保存到IPFS系统中去，将信息保存到数据库中
 */
public class TvisJsonRawListener {
    public final static Logger logger = LoggerFactory.getLogger(TvisJsonRawListener.class);

    @KafkaListener(id = "TvisJsonRawListener", topics = "tvis")
    public void listen(String json) {
        logger.info("TvisJsonRawListener 监听到消息!");
        // 生成临时JSON文件，上传到IPFS得到jsonHash
        logger.info("    raw: 上传到IPFS...");
        // 获取imageHash、cameraId、streamId、pts，将其存入mysql数据库中
        logger.info("    raw: 保存到数据库中...");
    }
}
