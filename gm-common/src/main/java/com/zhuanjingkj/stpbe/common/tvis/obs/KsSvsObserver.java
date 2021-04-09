package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.mapper.DeviceMapper;
import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleVO;
import org.apache.commons.lang.StringUtils;
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

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public void notifyObserver(VehicleVO vo) {
        System.out.println("KsSvsObserver...");
        String vType = vo.getVehicleCxtzVo().getCllxzflCode();
        /**
         * cameraId = -1 时需要根据streamId查找正确的cameraId
         */
        long cameraId = vo.getCameraId();
        String code = "";
        if(cameraId == -1) {
            long streamId = vo.getStreamId();
            String newCameraId = deviceMapper.getCameraIdByStreamId(streamId);
            if(StringUtils.isNotBlank(newCameraId)) {
                code = newCameraId;
            }
        } else {
            code = cameraId + "";
        }

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
        } else if("11".equals(vType)) { //大型客车
            redisTemplate.opsForValue().increment("ks_svs_medium_bus");
        } else if("12".equals(vType)) { //中型客车
            redisTemplate.opsForValue().increment("ks_svs_light_bus");
        } else if("22".equals(vType)) { //轻微型货车
            redisTemplate.opsForValue().increment("ks_svs_mini_truck");
        } else if("23".equals(vType)) { //三轮车
            redisTemplate.opsForValue().increment("ks_svs_tricycle");
        } else if("30".equals(vType)) { //摩托车
            redisTemplate.opsForValue().increment("ks_svs_motorcycle");
        } else { //其他
            redisTemplate.opsForValue().increment("ks_svs_others");
        }
        //本日重点监控车辆区域分布图
        redisTemplate.opsForHash().increment("ks_svs_area", code, 1);
    }

    @Override
    public void initialize(Environment env) {

    }

}
