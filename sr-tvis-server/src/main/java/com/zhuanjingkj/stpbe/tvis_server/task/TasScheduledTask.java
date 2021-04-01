package com.zhuanjingkj.stpbe.tvis_server.task;

import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.common.tvis.ObserverThread;
import com.zhuanjingkj.stpbe.common.tvis.TvisStpOberverManager;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.common.util.DebugLogger;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TasScheduledTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(TasScheduledTask.class);
    private static boolean isInitialized = false;
    private final static long TST_INTERVAL = 1; // 每*毫秒运行一次
    private static List<ITvisStpObserver> observers = new ArrayList<>();
    private static boolean isFirstRun = true;
    @Value("${observer.thread.num}")
    private int observerThreadNum;
    @Value("${ignore.redis.list.num}")
    private int ignoreRedisListNum;
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

    private void initialize() {
        if (isInitialized) {
            return ;
        }
        TvisUtil.rotateTvisJsonTbl(tvisJsonMapper);
        isInitialized = true;
    }

    public void run() {
        if (!isInitialized) {
            synchronized (logger) {
                initialize();
            }
        }
        tvisStpOberverManager.initialize(observers, environment);
        Thread thd = null;
        DebugLogger.log("##### yt: 启动Observer线程池");
        for (int i=0; i<observerThreadNum; i++) {
            thd = new Thread(new ObserverThread(observers));
            thd.start();
        }
        while (true) {
            try {
                runTasScheduledTask();
                Thread.sleep(TST_INTERVAL);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                System.out.println("##### sr-tvis-server::TasScheduledTask.run Exception: " + ex.getMessage() + "!");
            }
        }
    }

    public void runTasScheduledTask() {
        JSONObject jo = null;
        JSONObject prevJo = null;
        for (int i=0; i<ignoreRedisListNum; i++) {
            prevJo = jo;
            jo = (JSONObject) redisTemplate.opsForList().leftPop(PropUtil.getValue("VIDEO_RECOG_RST_REDIS_KEY"));
            if (null == jo) {
                jo = prevJo;
                break;
            }
        }
        if (null == jo) {
            return ;
        }
        String response = jo.toString();
        StringBuilder msg = null;
        long tvisJsonId = 0;
        tvisJsonId = redisTemplate.opsForValue().increment(PropUtil.getValue("TVIS_JSON_TBL_ID_KEY"));
        msg = new StringBuilder("{\"cameraId\": \"-1\", \"tvisJsonId\": "
                + tvisJsonId + ", \"json\": " + response + "}");
        String json = msg.toString();
        try {
            TvisUtil.processRawTvisJson(redisTemplate, tvisJsonMapper, json);
            TvisUtil.processStpTvisJson(observers, json);
        } catch (Exception ex) {
            DebugLogger.log("************************** exception: " + ex.getMessage() + "!");
        }
        /*
        // 从Redis中读出视频识别结果，将其发送到Kafka
        // 向Kafka的Topic发送请求
        kafkaTemplate.send("tvis", 0, msg.toString());
        logger.info("### Yantao ###: send to Kafka: json=" + msg.toString() + "!");
        kafkaTemplate.flush();*/
    }
}
