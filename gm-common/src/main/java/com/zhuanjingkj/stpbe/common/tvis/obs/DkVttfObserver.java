package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 首页车辆类型分类
 */
@Component
public class DkVttfObserver implements ITvisStpObserver {

    private static final String[] LCAR_CODE = {"11", "40"}; //大型车：大型客车 挂车
    private static final String[] CAR_CODE = {"12", "21", "22"}; //中型车：中型客车 重中型货车 轻微型货车
    private static final String[] SCAR_CODE = {"13", "14", "23", "30", "50"}; //小型车：小型客车 微型客车 三轮车 摩托车 电动自行车
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void notifyObserver(VehicleVo vo) {
        String vType = vo.getVehicleCxtzVo().getCllxflCode();
        Integer hour = LocalDateTime.now().getHour();
        Integer index = 0;
        if(hour % 2 == 0) {
            index = (hour + 2)/2 - 1;
        } else {
            index = (hour + 1)/2 - 1;
        }
        if(Arrays.asList(LCAR_CODE).contains(vType)) {
            Integer count = (int)(redisTemplate.opsForList().index("dk_vttf_lcar",index));
            redisTemplate.opsForList().set("dk_vttf_lcar", index, count + 1);
        } else if(Arrays.asList(CAR_CODE).contains(vType)) {
            Integer count = (int)(redisTemplate.opsForList().index("dk_vttf_car",index));
            redisTemplate.opsForList().set("dk_vttf_car", index, count + 1);
        } else if(Arrays.asList(SCAR_CODE).contains(vType)) {
            Integer count = (int)(redisTemplate.opsForList().index("dk_vttf_scar",index));
            redisTemplate.opsForList().set("dk_vttf_scar", index, count + 1);
        }
    }

    @Override
    public void initialize(Environment env) {
        if(!redisTemplate.hasKey("dk_vttf_lcar")) {
            redisTemplate.opsForList().rightPushAll("dk_vttf_lcar", 0,0,0,0,0,0,0,0,0,0,0,0);
        }

        if(!redisTemplate.hasKey("dk_vttf_car")) {
            redisTemplate.opsForList().rightPushAll("dk_vttf_car", 0,0,0,0,0,0,0,0,0,0,0,0);
        }

        if(!redisTemplate.hasKey("dk_vttf_scar")) {
            redisTemplate.opsForList().rightPushAll("dk_vttf_scar", 0,0,0,0,0,0,0,0,0,0,0,0);
        }
    }

}
