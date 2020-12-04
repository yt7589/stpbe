package com.zhuanjingkj.stpbe.tebs.scs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.util.Constant;
import com.zhuanjingkj.stpbe.common.util.Utils;
import com.zhuanjingkj.stpbe.data.entity.image.VehicleInformation;
import com.zhuanjingkj.stpbe.data.rto.vehicle.MsgRTO;
import com.zhuanjingkj.stpbe.data.rto.vehicle.ResultRTO;
import com.zhuanjingkj.stpbe.data.rto.vehicle.VehicleInfoRTO;
import com.zhuanjingkj.stpbe.tebs.dto.CameraDTO;
import com.zhuanjingkj.stpbe.tebs.rto.ImageRTO;
import com.zhuanjingkj.stpbe.tebs.rto.VehicleInformationRTO;
import com.zhuanjingkj.stpbe.tebs.service.CreateService;
import com.zhuanjingkj.stpbe.tebs.service.InsertService;
import com.zhuanjingkj.stpbe.tebs.service.SelectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author by guoqiang
 * date on 2020.12.01
 **/


public class PlatformDataListener {

    @Autowired
    SelectService selectService;

    @Autowired
    CreateService createService;

    @Autowired
    InsertService insertService;

    @KafkaListener(id = "zjkj003", topics = "tvis")
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
        ImageRTO image = this.generateImage(imageTableName,result,camera);
        //插入图片表
        insertService.insertImage(image);
        List<VehicleInformation> list = this.generateVehicleInformation(vehicleInfoList,image);
        VehicleInformationRTO vehicleInformationRTO = new VehicleInformationRTO();
        String vehicleInformationTableName = createService.createNewVehicleIfon();
        vehicleInformationRTO.setVehicleInformationList(list);
        vehicleInformationRTO.setVehicleInformationTableName(vehicleInformationTableName);
        insertService.insertVehicleInfo(vehicleInformationRTO);
    }

    /**
     * 生成图片实体类
     * @param imageTableName
     * @param result
     * @param camera
     */
    private ImageRTO generateImage(String imageTableName,ResultRTO result,CameraDTO camera){
        ImageRTO image = new ImageRTO();
        String uid = Utils.UUID();
        image.setUid(uid);
        image.setTableName(imageTableName);
        image.setCameraId(camera.getCameraId());
        image.setImageUrl(result.getImageUrl());
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        String dateStr = simpleDateFormat.format(date);
        image.setUploadTime(dateStr);
        image.setLat(camera.getLat());
        image.setLng(camera.getLng());
        image.setVideoStreamId(Long.valueOf(result.getStreamId()));
        image.setVehicleNum(Integer.valueOf(result.getVehicleNumber()));
        if(image.getVehicleNum() ==-1){
            //图片格式的图片类型为0
            image.setImageType(Constant.IMAGE_IMAGE_TYPE);
            image.setPresentationTimeStamp(dateStr);
        }else{
            //视频流的图片格式为1
            image.setImageType(Constant.VIDEO_IMAGE_TYPE);
            Date date1 = new Date(result.getTimeStamp());
            image.setPresentationTimeStamp(simpleDateFormat.format(date1));
        }
        return image;
    }

    /**
     * 生成车辆基本信息实体类列表
     * @param vehicleInfoList
     * @param imageRTO
     * @return
     */
    private  List<VehicleInformation> generateVehicleInformation(List<VehicleInfoRTO> vehicleInfoList,ImageRTO imageRTO){
        Long imageId = selectService.getImage(imageRTO.getTableName(),imageRTO.getUid());
        List<VehicleInformation> list = new ArrayList<>();
        for(VehicleInfoRTO vehicleInfoRTO : vehicleInfoList){
            VehicleInformation v = new VehicleInformation();
            String uid = Utils.UUID();
            v.setUid(uid);
            String positionInfo = JSONObject.toJSONString(vehicleInfoRTO.getPosition());
            v.setPositionInfo(positionInfo);
            v.setOrderNumber(vehicleInfoRTO.getOrderNumber());
            String vehicleModelInfo = JSONObject.toJSONString(vehicleInfoRTO.getVehicleModel());
            v.setVehicleModelInfo(vehicleModelInfo);
            v.setVehicleVector(vehicleInfoRTO.getVehicleVector());
            String individuationInfo = JSONObject.toJSONString(vehicleInfoRTO.getIndividuationFeature());
            v.setIndividuationInfo(individuationInfo);
            String licencePlate = JSONObject.toJSONString(vehicleInfoRTO.getLicencePlate());
            v.setLicencePlate(licencePlate);
            String drivingBehavior = JSONObject.toJSONString(vehicleInfoRTO.getDrivingBehavior());
            v.setDrivingBehavior(drivingBehavior);
            v.setImageTableName(imageRTO.getTableName());
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
            String dateStr = simpleDateFormat.format(date);
            v.setCreatTime(dateStr);
            v.setImageId(imageId);
            list.add(v);
        }
        return list;
    }
}
