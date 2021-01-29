package com.zhuanjingkj.stpbe.tebs.scs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.common.tvis.TvisStpOberverManager;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.common.tvis.obs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析JSON字符串，将信息保存到各个界面元素对应的Redis对象中
 */
public class TvisJsonStpListener {
    private final static Logger logger = LoggerFactory.getLogger(TvisJsonStpListener.class);
    private static List<ITvisStpObserver> observers = new ArrayList<>();
    private static boolean isFirstRun = true;
    @Autowired
    private Environment environment;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TvisStpOberverManager tvisStpOberverManager;

    @KafkaListener(id = "TvisJsonStpListener", topics = "tvis")
    public void listen(String json) {
        if (isFirstRun) {
            tvisStpOberverManager.initialize(observers, environment);
            isFirstRun = false;
        }
        TvisUtil.processStpTvisJson(observers, json);
    }
}
