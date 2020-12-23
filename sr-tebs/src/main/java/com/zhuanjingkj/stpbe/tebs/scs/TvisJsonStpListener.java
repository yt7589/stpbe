package com.zhuanjingkj.stpbe.tebs.scs;

import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
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
        observers.add(new DkVtieObserver());
        observers.add(new DkVtpObserver());
    }

    @KafkaListener(id = "TvisJsonStpListener", topics = "tvis")
    public void listen(String json) {
        System.out.println("TvisJsonStpListener监听到消息");
        System.out.println("    解析为值对象");
        VehicleVo vo = null;
        for (ITvisStpObserver obs : observers) {
            obs.notifyObserver(vo);
        }
    }
}
