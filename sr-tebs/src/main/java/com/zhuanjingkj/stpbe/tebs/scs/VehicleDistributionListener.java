package com.zhuanjingkj.stpbe.tebs.scs;

import com.alibaba.fastjson.JSON;
import com.zhuanjingkj.stpbe.common.util.Constant;
import com.zhuanjingkj.stpbe.common.util.Utils;
import com.zhuanjingkj.stpbe.data.entity.VehicleDistribution;
import com.zhuanjingkj.stpbe.data.rto.vehicle.MsgRTO;
import com.zhuanjingkj.stpbe.data.rto.vehicle.ResultRTO;
import com.zhuanjingkj.stpbe.data.rto.vehicle.VehicleInfoRTO;
import com.zhuanjingkj.stpbe.tebs.dto.CameraDTO;
import com.zhuanjingkj.stpbe.tebs.service.InsertService;
import com.zhuanjingkj.stpbe.tebs.service.SelectService;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * author by guoqiang
 * date on 2020.12.04
 **/
public class VehicleDistributionListener {

    @Autowired
    SelectService selectService;

    @Autowired
    RedissonClient redissonClient;

    @Value("${license.plate.head}")
    private String licensePlateHead;

    @Autowired
    InsertService insertService;


    @KafkaListener(id = "zjkj004", topics = "tvis")
    public void listen(String value){
        if (StringUtils.isEmpty(value)){
            return;
        }
        MsgRTO msg = JSON.parseObject(value,MsgRTO.class);
        if(msg==null || StringUtils.isEmpty(msg.getCameraId())
                || msg.getResult()==null || msg.getResult().getVehicleInfoList() ==null
                || msg.getResult().getVehicleInfoList().size() == 0){
            return;
        }
        CameraDTO camera = selectService.getCamera(msg.getCameraId());
        if(camera==null || camera.getCameraId()==null){
            return;
        }
        ResultRTO result = msg.getResult();
        List<VehicleInfoRTO> vehicleInfoList = result.getVehicleInfoList();
        long nativeVehicle = 0L;
        long otherVehicle = 0L;
        for(VehicleInfoRTO vehicleInfoRTO : vehicleInfoList){
            if(vehicleInfoRTO.getLicencePlate()==null
            || vehicleInfoRTO.getLicencePlate().getLicencePlateNumber()==null){
                otherVehicle++;
                continue;
            }
            if(vehicleInfoRTO.getLicencePlate().getLicencePlateNumber().contains(licensePlateHead)){
                nativeVehicle++;
            }else{
                otherVehicle++;
            }
        }
        RLock lock = redissonClient.getLock("lock1");
        lock.lock(10, TimeUnit.SECONDS);
        try{
            List<VehicleDistribution> list = selectService.getVehicleDistribution();
            if(list ==null || list.size() ==0){
                list = new ArrayList<>();
                VehicleDistribution vehicleDistributionNative = new VehicleDistribution();
                vehicleDistributionNative.setDate(Utils.getCurrentDate());
                vehicleDistributionNative.setVehicleDistributionName(Constant.NATIVE_VEHICLE);
                vehicleDistributionNative.setVehicleDistributionNum(nativeVehicle);

                VehicleDistribution vehicleDistributionOther = new VehicleDistribution();
                vehicleDistributionOther.setDate(Utils.getCurrentDate());
                vehicleDistributionOther.setVehicleDistributionName(Constant.OTHER_VEHICLE);
                vehicleDistributionOther.setVehicleDistributionNum(otherVehicle);
                list.add(vehicleDistributionNative);
                list.add(vehicleDistributionOther);
                insertService.insertVehicleDistribution(list);
            }else {
                for(VehicleDistribution vehicleDistribution:list){
                    if(vehicleDistribution.getVehicleDistributionName().contains(Constant.NATIVE_VEHICLE)){
                        vehicleDistribution.setVehicleDistributionNum(vehicleDistribution.getVehicleDistributionNum()+nativeVehicle);
                    }
                    if(vehicleDistribution.getVehicleDistributionName().contains(Constant.OTHER_VEHICLE)){
                        vehicleDistribution.setVehicleDistributionNum(vehicleDistribution.getVehicleDistributionNum()+otherVehicle);
                    }
                }
                insertService.updateVehicleDistribution(list);
            }
        }finally {
            lock.unlock();
        }
    }
}
