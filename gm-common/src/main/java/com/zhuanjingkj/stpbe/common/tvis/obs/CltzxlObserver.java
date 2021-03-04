package com.zhuanjingkj.stpbe.common.tvis.obs;

import com.zhuanjingkj.stpbe.common.mgq.GrqEngine;
import com.zhuanjingkj.stpbe.common.tvis.ITvisStpObserver;
import com.zhuanjingkj.stpbe.data.vo.VehicleCltzxlVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleCxtzVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.data.vo.VehicleWztzVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 车辆特征向量观察者，将车辆特征向量加上tvisJsonId、wztz一起保存到Milvus中，
 * 供以图搜车进行查询
 */
@Component
public class CltzxlObserver implements ITvisStpObserver {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void notifyObserver(VehicleVo vo) {
        if (null == redisTemplate) {
            System.out.println("##### 直接返回 #####");
            return ;
        }
        VehicleWztzVo vehicleWztzVo = vo.getVehicleWztzVo();
        VehicleCxtzVo vehicleCxtzVo = vo.getVehicleCxtzVo();
        VehicleCltzxlVo vehicleCltzxlVo = vo.getVehicleCltzxlVo();
        try {
            String partitionTag = GrqEngine.getPartitionTag(
                    vehicleWztzVo.getPsfx(),
                    vehicleCxtzVo.getCllxflCode(),
                    vehicleCxtzVo.getCllxzflCode()
            );
            if (partitionTag.indexOf("tail_") >= 0) {
                System.out.println("忽略车尾数据......");
                return;
            }
            long tid = GrqEngine.insertRecord(redisTemplate, partitionTag, vo);
        } catch (Exception ex) {
            System.out.println("CltzxlObserver.notifyObserver Exception: " + ex.getMessage() + " !!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    @Override
    public void initialize(Environment env) {

    }
}
