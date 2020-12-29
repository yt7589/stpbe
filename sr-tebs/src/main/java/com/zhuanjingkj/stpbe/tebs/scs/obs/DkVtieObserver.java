package com.zhuanjingkj.stpbe.tebs.scs.obs;

import com.alibaba.fastjson.JSONObject;
import com.netflix.discovery.converters.Auto;
import com.zhuanjingkj.stpbe.common.AppRegistry;
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
    private String hphmNativePrefix = null;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void notifyObserver(VehicleVo vo) {
        String hphm = vo.getVehicleHptzVO().getHphm();
        if (hphm != null && !hphm.equals("")) {
            if (hphm.indexOf(hphmNativePrefix) >= 0) {
                Long ti = redisTemplate.opsForValue().increment("dkInternalNum");
                redisTemplate.opsForValue().set("dkInternalNum0", "" + ti);
                System.out.println("    ##### 本地号牌：" + hphm + "; num="
                        + ti + "!");
            } else {
                Long te = redisTemplate.opsForValue().increment("dkExternalNum");
                redisTemplate.opsForValue().set("dkExternalNum0", "" + te);
                System.out.println("    ##### 外埠号牌：" + hphm + "; num="
                        + te + "!");
            }
        }
    }

    @Override
    public void initialize(Environment env) {
        hphmNativePrefix = env.getProperty("hphm.native.prefix");
    }
}
