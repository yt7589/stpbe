package com.zhuanjingkj.stpbe.tebs.scs.obs;

import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.tebs.scs.ITvisStpObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 首页过车量统计
 */
@Component
public class DkHtfsObserver implements ITvisStpObserver {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void notifyObserver(VehicleVo vo) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        redisTemplate.opsForValue().increment("dk_htfs_" + date);
    }

    @Override
    public void initialize(Environment env) {

    }
}
