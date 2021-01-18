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
public class KsRssObserver implements ITvisStpObserver {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void notifyObserver(VehicleVo vo) {
        long cameraId = vo.getCameraId();
        String hphm = vo.getVehicleHptzVO().getHphm();
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //统计同一辆车在同一个设备下通过的次数
        if(redisTemplate.hasKey("ks_rss_lsvs_total")) {
            redisTemplate.opsForHash().increment("ks_rss_lsvs_total",  hphm + "|" + cameraId, 1);
        }
        redisTemplate.opsForHash().put("ks_rss_lsvs_time", hphm + "|" + cameraId, date);
        redisTemplate.opsForList().leftPush("ks_rss_lsvs_list", hphm + "|" + cameraId);
    }

    @Override
    public void initialize(Environment env) {
        if(!redisTemplate.hasKey("ks_rss_lsvs_list")) {
            redisTemplate.opsForList().rightPushAll("ks_rss_lsvs_list",0);
        }
    }
}
