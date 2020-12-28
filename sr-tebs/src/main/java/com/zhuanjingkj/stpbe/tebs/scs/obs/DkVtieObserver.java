package com.zhuanjingkj.stpbe.tebs.scs.obs;

import com.alibaba.fastjson.JSONObject;
import com.netflix.discovery.converters.Auto;
import com.zhuanjingkj.stpbe.data.vo.VehicleHptzVO;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.tebs.scs.ITvisStpObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 首页数据看板页面左侧第一行第一个报表，本地外埠车辆统计
 */
@Component
public class DkVtieObserver implements ITvisStpObserver {
    @Autowired
    private Environment environment;
    @Autowired
    private RedisTemplate redisTemplate;
    private String hphmNativePrefix;

    public DkVtieObserver() {
        hphmNativePrefix = System.getProperty("hphm.native.prefix", "京");
        System.out.println("    v000 ############# hphmNativePrefix=" + hphmNativePrefix + "!!!!!!!!!!!!!!!!!!!!");
        System.out.println("    v001 " + environment + "!");
        System.out.println("    v002 redisTemplate=" + redisTemplate + "!!!!!!!!!!!!!!");
    }

    @Override
    public void notifyObserver(VehicleVo vo) {
        String hphm = vo.getVehicleHptzVO().getHphm();
        System.out.println("    ????? hphm=" + hphm + "; hphmNativePrefix=" + hphmNativePrefix + "!");
        if (hphm != null && !hphm.equals("")) {
            if (hphm.indexOf(hphmNativePrefix) >= 0) {
                System.out.println("    ##### 本地号牌：" + hphm + "!");
            } else {
                System.out.println("    ##### 外埠号牌：" + hphm + "!");
            }
        }
    }
}
