package com.zhuanjingkj.stpbe.tebs.scs.obs;

import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.tebs.scs.ITvisStpObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class KsAsObserver implements ITvisStpObserver {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void notifyObserver(VehicleVo vo) {
        long cameraId = vo.getCameraId();
        String hphm = vo.getVehicleHptzVO().getHphm();
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //统计同一辆车在同一个设备下通过的次数
        if(redisTemplate.hasKey("ks_as_lsvs_count")) {
            redisTemplate.opsForHash().increment("ks_as_lsvs_total",  hphm + "|" + cameraId, 1);
        }
        redisTemplate.opsForHash().put("ks_as_lsvs_time", hphm + "|" + cameraId, date);
        redisTemplate.opsForList().leftPush("ks_as_lsvs_list", hphm + "|" + cameraId);
    }

    @Override
    public void initialize(Environment env) {
        if(!redisTemplate.hasKey("ks_as_lsvs_list")) {
            redisTemplate.opsForList().rightPushAll("ks_as_lsvs_list",0);
        }
    }
}
