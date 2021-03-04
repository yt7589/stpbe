package com.zhuanjingkj.stpbe.tvis_server.task;

import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.common.tvis.TvisStpOberverManager;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TasScheduledTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(TasScheduledTask.class);
    private final static long TST_INTERVAL = 100; // 每*毫秒运行一次
    private static List<ITvisStpObserver> observers = new ArrayList<>();
    private static boolean isFirstRun = true;
    @Autowired
    private Environment environment;
    @Autowired
    private RedisTemplate redisTemplate;
    //@Autowired
    //private KafkaTemplate<Integer, String> kafkaTemplate;
    @Autowired
    private TvisJsonMapper tvisJsonMapper;
    @Autowired
    private TvisStpOberverManager tvisStpOberverManager;

    public void run() {
        while (true) {
            runTasScheduledTask();
            try {
                Thread.sleep(TST_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //@Async("tvisServerPool")
    //@Scheduled(cron = "*/1 * * * * ?")
    public void runTasScheduledTask() {
        JSONObject jo = (JSONObject) redisTemplate.opsForList().leftPop(AppConst.VIDEO_RECOG_RST_REDIS_KEY);
        if (null == jo) {
            return ;
        }
        String response = jo.toString();
        StringBuilder msg = null;
        long tvisJsonId = 0;
        synchronized (redisTemplate) {
            tvisJsonId = redisTemplate.opsForValue().increment(AppConst.TVIS_JSON_TBL_ID_KEY);
            msg = new StringBuilder("{\"cameraId\": \"-1\", \"tvisJsonId\": "
                    + tvisJsonId + ", \"json\": " + response + "}");
        }
        String json = msg.toString();
        TvisUtil.processRawTvisJson(redisTemplate, tvisJsonMapper, json);
        if (isFirstRun) {
            tvisStpOberverManager.initialize(observers, environment);
            isFirstRun = false;
        }
        TvisUtil.processStpTvisJson(observers, json);
        /*
        // 从Redis中读出视频识别结果，将其发送到Kafka
        // 向Kafka的Topic发送请求
        kafkaTemplate.send("tvis", 0, msg.toString());
        logger.info("### Yantao ###: send to Kafka: json=" + msg.toString() + "!");
        kafkaTemplate.flush();*/
    }
}
