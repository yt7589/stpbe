package com.zhuanjingkj.stpbe.tebs.scs;

import com.alibaba.fastjson.JSON;
import com.zhuanjingkj.stpbe.data.rto.vehicle.MsgRTO;
import com.zhuanjingkj.stpbe.data.rto.vehicle.ResultRTO;
import com.zhuanjingkj.stpbe.data.rto.vehicle.VehicleInfoRTO;
import com.zhuanjingkj.stpbe.tebs.dto.CameraDTO;
import com.zhuanjingkj.stpbe.tebs.service.CreateService;
import com.zhuanjingkj.stpbe.tebs.service.SelectService;
import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * author by guoqiang
 * date on 2020.12.01
 **/

@Component
public class PlatformDataListener {

    @Autowired
    SelectService selectService;
    @Autowired
    CreateService createService;

    @KafkaListener(id = "zjkj", topics = "tvis")
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
        String imageTableName = createService.createNewImageTable();
    }
}
