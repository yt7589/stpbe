package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 特殊车辆=》分类/区域统计
 */
@Component
public class KsSvsObserver implements ITvisStpObserver {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void notifyObserver(VehicleVo vo) {
        String vType = vo.getVehicleCxtzVo().getCllxzflCode();
        //本日重点监控车辆车型构成
        if("131".equals(vType)) {  //轿车
            redisTemplate.opsForValue().increment("ks_svs_car");
        } else if("132".equals(vType)) { //SUV
            redisTemplate.opsForValue().increment("ks_svs_suv");
        } else if("133".equals(vType)) { //MPV
            redisTemplate.opsForValue().increment("ks_svs_mpv");
        } else if("134".equals(vType)) { //面包车
            redisTemplate.opsForValue().increment("ks_svs_van");
        } else if("211".equals(vType)) { //罐式货车
            redisTemplate.opsForValue().increment("ks_svs_tank_truck");
        } else if("216".equals(vType)) { //普通货车
            redisTemplate.opsForValue().increment("ks_svs_normal_truck");
        } else if("212".equals(vType)) { //箱式货车
            redisTemplate.opsForValue().increment("ks_svs_van_truck");
        } else if("213".equals(vType)) { //栏板式货车
            redisTemplate.opsForValue().increment("ks_svs_slab_truck");
        } else if("214".equals(vType)) { //平板式货车
            redisTemplate.opsForValue().increment("ks_svs_flat_truck");
        } else if("215".equals(vType)) { //仓栅式货车
            redisTemplate.opsForValue().increment("ks_svs_grate_truck");
        } else { //其他
            redisTemplate.opsForValue().increment("ks_svs_others");
        }
        //本日重点监控车辆区域分布图
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000001", 1);
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000002", 2);
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000003", 3);
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000004", 4);
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000005", 5);
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000006", 6);
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000007", 7);
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000008", 8);
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000009", 9);
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000010", 10);
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000011", 11);
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000012", 12);
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000013", 13);
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000014", 14);
        redisTemplate.opsForHash().increment("ks_svs_area", "C0000015", 10);
    }

    @Override
    public void initialize(Environment env) {

    }

}
