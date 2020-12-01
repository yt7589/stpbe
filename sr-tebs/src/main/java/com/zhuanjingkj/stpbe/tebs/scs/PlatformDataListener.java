package com.zhuanjingkj.stpbe.tebs.scs;

import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * author by guoqiang
 * date on 2020.12.01
 **/

@Component
public class PlatformDataListener {

    @KafkaListener(id = "zjkj", topics = "tvis")
    public void listen(String value){
        if (StringUtils.isNotEmpty(value)){

        }
    }
}
