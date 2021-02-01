package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import org.apache.commons.lang.StringUtils;
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
        int random = (int)(Math.random()*15) + 1;
        String code ="";
        if(random < 10) {
            code = "C000000" + random;
        } else {
            code = "C00000" + random;
        }
        if(StringUtils.isBlank(hphm)) {
            hphm = "豫A888888";
        }
        redisTemplate.opsForHash().increment("ks_as_lsvs_total",  hphm + "|" + code, 1);
        redisTemplate.opsForHash().put("ks_as_lsvs_time", hphm + "|" + code, date);
        redisTemplate.opsForList().leftPush("ks_as_lsvs_list", hphm + "|" + code);
    }

    @Override
    public void initialize(Environment env) {
        if(!redisTemplate.hasKey("ks_as_lsvs_list")) {
            redisTemplate.opsForList().rightPushAll("ks_as_lsvs_list","0");
        }
    }
}
