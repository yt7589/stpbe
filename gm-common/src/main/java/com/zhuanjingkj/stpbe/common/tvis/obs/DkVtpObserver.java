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
        String vType = vo.getVehicleCxtzVo().getCllxzflCode();
        if("131".equals(vType)) {  //轿车
            redisTemplate.opsForValue().increment("DK_VT_CAR");
        } else if("132".equals(vType)) { //SUV
            redisTemplate.opsForValue().increment("DK_VT_SUV");
        } else if("133".equals(vType)) { //MPV
            redisTemplate.opsForValue().increment("DK_VT_MPV");
        } else if("134".equals(vType)) { //面包车
            redisTemplate.opsForValue().increment("DK_VT_VAN");
        } else if("211".equals(vType)) { //罐式货车
            redisTemplate.opsForValue().increment("DK_VT_TANK_TRUCK");
        } else if("216".equals(vType)) { //普通货车
            redisTemplate.opsForValue().increment("DK_VT_NORMAL_TRUCK");
        } else { //其他
            redisTemplate.opsForValue().increment("DK_VT_OTHERS");
        }

    }

    @Override
    public void initialize(Environment env) {

    }
}
