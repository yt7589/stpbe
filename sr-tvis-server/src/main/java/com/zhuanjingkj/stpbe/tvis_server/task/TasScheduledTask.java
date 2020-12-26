package com.zhuanjingkj.stpbe.tvis_server.task;

import com.alibaba.fastjson.JSONObject;
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

    //@Async("tvisServerPool")
    //@Scheduled(cron = "*/1 * * * * ?")
    public void runTasScheduledTask() {
        JSONObject jo = (JSONObject) redisTemplate.opsForList().leftPop(AppConst.VIDEO_RECOG_RST_REDIS_KEY);
        if (null == jo) {
            return ;
        }
        String response = jo.toString();
        // 从Redis中读出视频识别结果，将其发送到Kafka
        // 向Kafka的Topic发送请求
        StringBuilder msg = null;
        long tvisJsonId = 0;
        synchronized (redisTemplate) {
            tvisJsonId = redisTemplate.opsForValue().increment(AppConst.TVIS_JSON_TBL_ID_KEY);
            msg = new StringBuilder("{\"cameraId\": \"-1\", \"tvisJsonId\": "
                    + tvisJsonId + ", \"json\": " + response + "}");
        }
        kafkaTemplate.send("tvis", 0, msg.toString());
        logger.info("send to Kafka: tvisJsonId=" + tvisJsonId + "!");
        kafkaTemplate.flush();
    }
}
