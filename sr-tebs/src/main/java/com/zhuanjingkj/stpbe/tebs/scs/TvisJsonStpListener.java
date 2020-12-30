package com.zhuanjingkj.stpbe.tebs.scs;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.tebs.scs.obs.CltzxlObserver;
import com.zhuanjingkj.stpbe.tebs.scs.obs.DkVtieObserver;
import com.zhuanjingkj.stpbe.tebs.scs.obs.DkVtpObserver;
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
    private CltzxlObserver cltzxlObserver; // 车辆特征向量保存到Milvus中
    @Autowired
    private DkVtieObserver dkVtieObserver; // 首页数据看板左侧第一行第一个：本地外埠车辆占比
    @Autowired
    private DkVtpObserver dkVtpObserver; // 首页数据看板左侧第一行第二个：车辆类型饼图

    @KafkaListener(id = "TvisJsonStpListener", topics = "tvis")
    public void listen(String json) {
        if (isFirstRun) {
            cltzxlObserver.initialize(environment);
            observers.add(cltzxlObserver);
            dkVtieObserver.initialize(environment);
            observers.add(dkVtieObserver);
            dkVtpObserver.initialize(environment);
            observers.add(dkVtpObserver);
            isFirstRun = false;
        }
        System.out.println("    解析为值对象");
        JSONObject rawJo = JSONObject.parseObject(json);
        long tvisJsonId = rawJo.getLong("tvisJsonId");
        System.out.println("json:" + json + "!");
        System.out.println("### BUG001 ###: TvisJsonStpListener.listen tvisJsonId=" + tvisJsonId + "!");
        JSONObject rstJo = rawJo.getJSONObject("json");
        List<VehicleVo> vehs = TvisUtil.parseTvisJson(rstJo.toJSONString());
        long vehsIdx = 0;
        for (VehicleVo veh : vehs) {
            veh.setTvisJsonId(tvisJsonId);
            veh.setVehsIdx(vehsIdx);
            vehsIdx++;
            for (ITvisStpObserver obs : observers) {
                obs.notifyObserver(veh);
            }
        }
    }
}
