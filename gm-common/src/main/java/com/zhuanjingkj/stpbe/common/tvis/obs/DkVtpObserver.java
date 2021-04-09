package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVO;
import org.apache.commons.lang.StringUtils;
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
    public void notifyObserver(VehicleVO vo) {
        System.out.println("DkVtpObserver...");
        String vType = vo.getVehicleCxtzVo().getCllxzflCode();
        String cllxfl = vo.getVehicleCxtzVo().getCllxflCode();
        if (StringUtils.isBlank(vType)) {
            vType = cllxfl;
        }
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
        } else if("11".equals(vType)) { //大型客车
            redisTemplate.opsForValue().increment("dk_vt_medium_bus");
        } else if("12".equals(vType)) { //中型客车
            redisTemplate.opsForValue().increment("dk_vt_light_bus");
        } else if("22".equals(vType)) { //轻微型货车
            redisTemplate.opsForValue().increment("dk_vt_mini_truck");
        } else if("23".equals(vType)) { //三轮车
            redisTemplate.opsForValue().increment("dk_vt_tricycle");
        } else if("30".equals(vType)) { //摩托车
            redisTemplate.opsForValue().increment("dk_vt_motorcycle");
        } else { //其他
            redisTemplate.opsForValue().increment("dk_vt_others");
        }

    }

    @Override
    public void initialize(Environment env) {

    }
}
