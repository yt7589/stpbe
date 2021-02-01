package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 首页过车量统计
 */
@Component
public class DkHtfsObserver implements ITvisStpObserver {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void notifyObserver(VehicleVo vo) {
        System.out.println("DkHtfsObserver...");
        redisTemplate.opsForValue().increment("dk_htfs_today");
        redisTemplate.opsForValue().increment("dk_htfs_month");
    }

    @Override
    public void initialize(Environment env) {

    }

}
