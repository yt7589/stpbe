package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 首页饼图数据统计
 */
@Component
public class DkVtpObserver implements ITvisStpObserver {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void notifyObserver(VehicleVo vo) {
        System.out.println("DkVtpObserver...");
        String vType = vo.getVehicleCxtzVo().getCllxzflCode();
        if("131".equals(vType)) {  //轿车
            redisTemplate.opsForValue().increment("dk_vt_car");
        } else if("132".equals(vType)) { //SUV
            redisTemplate.opsForValue().increment("dk_vt_suv");
        } else if("133".equals(vType)) { //MPV
            redisTemplate.opsForValue().increment("dk_vt_mpv");
        } else if("134".equals(vType)) { //面包车
            redisTemplate.opsForValue().increment("dk_vt_van");
        } else if("211".equals(vType)) { //罐式货车
            redisTemplate.opsForValue().increment("dk_vt_tank_truck");
        } else if("216".equals(vType)) { //普通货车
            redisTemplate.opsForValue().increment("dk_vt_normal_truck");
        } else { //其他
            redisTemplate.opsForValue().increment("dk_vt_others");
        }

    }

    @Override
    public void initialize(Environment env) {

    }
}
