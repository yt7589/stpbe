package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 首页分时段过车统计
 */
@Component
public class DkTitfObserver implements ITvisStpObserver {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void notifyObserver(VehicleVo vo) {
        System.out.println("DkTitfObserver...");
        int hour = LocalDateTime.now().getHour();
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        if(hour >= 0 && hour < 4 ) { //0~4时过车量
            redisTemplate.opsForValue().increment("dk_titf_" + date + "04");
        } else if(hour >=4 && hour < 8){ //4~8时过车量
            redisTemplate.opsForValue().increment("dk_titf_" + date + "08");
        } else if(hour >=8 && hour < 12){ //8~12时过车量
            redisTemplate.opsForValue().increment("dk_titf_" + date + "12");
        } else if(hour >=12 && hour < 16){ //12~16时过车量
            redisTemplate.opsForValue().increment("dk_titf_" + date + "16");
        } else if(hour >=16 && hour < 20){ //16~20时过车量
            redisTemplate.opsForValue().increment("dk_titf_" + date + "20");
        } else if(hour >=20 && hour < 24){ //20~24时过车量
            redisTemplate.opsForValue().increment("dk_titf_" + date + "24");
        }
    }

    @Override
    public void initialize(Environment env) {

    }

}
