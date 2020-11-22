package com.zhuanjingkj.stpbe.tebs.scs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class TvisJsonRawListener {
    public final static Logger logger = LoggerFactory.getLogger(TvisJsonRawListener.class);

    @KafkaListener(id = "zjkj", topics = "tvis")
    public void listen(String json) {
        logger.info("监听到消息：" + json + "!");
    }
}
