package com.zhuanjingkj.stpbe.tebs.scs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class TvisKbTitfListener {
    private final static Logger logger = LoggerFactory.getLogger(TvisKbTitfListener.class);

    @KafkaListener(id = "zjkj001", topics = "tvis")
    public void listen(String json) {
        logger.info("交通流量消息：" + json + "!");
    }
}
