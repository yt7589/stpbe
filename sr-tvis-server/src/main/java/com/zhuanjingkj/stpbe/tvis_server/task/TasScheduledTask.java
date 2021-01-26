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
public class TasScheduledTask implements Runnable {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;
    private static Logger logger = LoggerFactory.getLogger(TasScheduledTask.class);

    public void run() {
        while (true) {
            runTasScheduledTask();
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //@Async("tvisServerPool")
    //@Scheduled(cron = "*/1 * * * * ?")
    public void runTasScheduledTask() {
        logger.info("##### runTasScheduledTask 1");
        JSONObject jo = (JSONObject) redisTemplate.opsForList().leftPop(AppConst.VIDEO_RECOG_RST_REDIS_KEY);
        logger.info("##### runTasScheduledTask 2");
        if (null == jo) {
            logger.info("##### runTasScheduledTask 3");
            return ;
        }
        logger.info("##### runTasScheduledTask 4");
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
        System.out.println("msg:" + msg + "!");
        kafkaTemplate.send("tvis", 0, msg.toString());
        logger.info("send to Kafka: tvisJsonId=" + tvisJsonId + "!");
        kafkaTemplate.flush();
    }
}
