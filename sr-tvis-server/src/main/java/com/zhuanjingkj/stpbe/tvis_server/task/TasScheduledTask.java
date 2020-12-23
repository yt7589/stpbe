package com.zhuanjingkj.stpbe.tvis_server.task;

import com.zhuanjingkj.stpbe.common.AppConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TasScheduledTask {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;
    private static Logger logger = LoggerFactory.getLogger(TasScheduledTask.class);

    @Async("tvisServerPool")
    @Scheduled(cron = "*/1 * * * * ?")
    public void runTasScheduledTask() {
        logger.info("TasScheduledTask.run ... v0.0.1 " + System.currentTimeMillis() + "!");
        // String response = (String)redisTemplate.opsForValue().get(AppConst.VIDEO_RECOG_RST_REDIS_KEY);
        String response = (String)redisTemplate.opsForList().leftPop(AppConst.VIDEO_RECOG_RST_REDIS_KEY);
        // 从Redis中读出视频识别结果，将其发送到Kafka
        // 向Kafka的Topic发送请求
        StringBuilder msg = new StringBuilder("{\"cameraId\": \"-1\", \"json\": " + response + "}");
        kafkaTemplate.send("tvis", 0, msg.toString());
        logger.info("kafka 1 " + response + "!");
        kafkaTemplate.flush();
        logger.info("kafka 2");
    }
}
