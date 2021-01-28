package com.zhuanjingkj.stpbe.tebs.scs.obs;

import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import org.springframework.beans.factory.annotation.Autowired;
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
            } else {
                Long te = redisTemplate.opsForValue().increment("dkExternalNum");
            }
        }
    }

    @Override
    public void initialize(Environment env) {
        hphmNativePrefix = env.getProperty("hphm.native.prefix");
    }
}
