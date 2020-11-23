package com.zhuanjingkj.stpbe.tebs.scs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class TvisKbVtieListener {
    private final static Logger logger = LoggerFactory.getLogger(TvisKbVtieListener.class);

    @KafkaListener(id="zjkj002", topics="tvis")
    public void listen(String json) {
        logger.info("车辆类型本市外市：" + json + "!");
    }
}
