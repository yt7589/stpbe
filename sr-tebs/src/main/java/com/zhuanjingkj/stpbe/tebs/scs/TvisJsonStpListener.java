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
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析JSON字符串，将信息保存到各个界面元素对应的Redis对象中
 */
public class TvisJsonStpListener {
    private final static Logger logger = LoggerFactory.getLogger(TvisJsonStpListener.class);
    private static List<ITvisStpObserver> observers = new ArrayList<>();

    public TvisJsonStpListener() {
        observers.add(new CltzxlObserver());
        observers.add(new DkVtieObserver());
        observers.add(new DkVtpObserver());
    }

    @KafkaListener(id = "TvisJsonStpListener", topics = "tvis")
    public void listen(String json) {
        System.out.println("TvisJsonStpListener监听到消息 ##########");
        System.out.println("    解析为值对象");
        JSONObject rawJo = JSONObject.parseObject(json);
        long tvisJsonId = rawJo.getLong("tvisJsonId");
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
