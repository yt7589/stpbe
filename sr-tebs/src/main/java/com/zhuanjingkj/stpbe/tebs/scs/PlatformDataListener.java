package com.zhuanjingkj.stpbe.tebs.scs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.common.util.Constant;
import com.zhuanjingkj.stpbe.common.util.Utils;
import com.zhuanjingkj.stpbe.data.rto.image.VehicleInformation;
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
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    InsertService insertService;

    //@KafkaListener(id = "aaa", topics = "tvis")
    @GetMapping
    public void listen(){
        String value ="{\n" +
                "\t\"cameraId\": \"111\",\n" +
                "\t\"result\": {\n" +
                "\t\t\"JLS\": \"1\",\n" +
                "\t\t\"StreamID\": \"1\",\n" +
                "\t\t\"CODE\": \"1\",\n" +
                "\t\t\"VEH\": [{\n" +
                "\t\t\t\"WZTZ\": {\n" +
                "\t\t\t\t\"PSFX\": \"1\",\n" +
                "\t\t\t\t\"CLWZ\": \"93,139,651,684\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"SXH\": \"1\",\n" +
                "\t\t\t\"CXTZ\": {\n" +
                "\t\t\t\t\"PPCX\": \"1019100002\",\n" +
                "\t\t\t\t\"CLLXZFL\": \"131\",\n" +
                "\t\t\t\t\"PPXHMS\": \"奥迪牌-A3-1999_2002_2004\",\n" +
                "\t\t\t\t\"CSYS\": \"J\",\n" +
                "\t\t\t\t\"CLPP\": \"1019\",\n" +
                "\t\t\t\t\"PPXHKXD\": \"14\",\n" +
                "\t\t\t\t\"CLLXFL\": \"13\",\n" +
                "\t\t\t\t\"CXNK\": \"1019100002101\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"CLTZXL\": \"0.103773,0.062859,0.124973,0.011065,-0.058767,-0.084989,-0.063091,0.026687,0.073273,0.070669,-0.005765,-0.030313,-0.007299,-0.036544,-0.024641,0.207359,-0.090661,0.304623,-0.044633,0.020178,-0.020643,-0.051096,-0.060348,-0.047516,-0.035614,-0.093684,0.009392,-0.027896,-0.061464,-0.073506,0.024548,0.034126,0.003905,0.180486,-0.025013,-0.025478,-0.007811,-0.090429,-0.061092,-0.020178,-0.063231,-0.017574,-0.058535,0.028733,-0.012553,0.146639,0.048539,0.069647,-0.046214,0.092986,0.073552,-0.043239,-0.039938,-0.005858,0.013483,-0.032266,-0.029012,-0.040077,-0.010600,0.030778,0.057093,0.029291,0.018783,0.038310,-0.032917,-0.062208,-0.062487,-0.043285,-0.053188,0.192853,0.010600,0.046493,0.016738,-0.058395,0.092614,0.019341,-0.080247,0.049655,0.066671,-0.029384,0.026129,0.086012,-0.059511,0.049934,0.008555,-0.053932,-0.048446,-0.039891,-0.076295,0.024734,-0.016273,0.004928,-0.044029,-0.075505,-0.055885,0.010693,0.017667,-0.078759,0.099681,-0.010228,-0.006137,-0.018272,-0.002697,0.156310,0.012553,-0.028082,-0.057744,0.060627,-0.010043,-0.019620,-0.059511,0.016738,-0.024734,0.046400,-0.028175,0.002418,0.029849,-0.019155,0.026966,-0.057465,0.006602,-0.012832,-0.054769,0.020550,0.003998,-0.068345,0.012274,-0.091777,-0.026826,-0.052956,0.019574,0.104702,-0.057372,0.020178,-0.016087,-0.030499,-0.001953,-0.066625,0.045284,-0.049097,0.010228,-0.013576,0.036265,0.008741,0.064718,0.015901,0.117999,-0.067275,-0.055373,0.002790,0.012646,-0.025385,-0.004370,-0.037287,0.007625,0.018783,0.062394,-0.042216,-0.032917,-0.018504,0.008369,-0.057558,-0.051049,0.032545,0.079224,-0.003440,-0.006881,-0.021945,0.009671,-0.053560,0.025292,-0.024548,0.070669,-0.053281,0.024362,-0.086570,-0.056443,-0.010600,0.016645,-0.059744,0.075133,-0.045842,0.060906,-0.001674,0.126740,-0.074389,-0.058163,-0.027524,0.048818,-0.115117,-0.027710,0.046214,-0.024362,-0.034312,0.078387,-0.001767,-0.020178,0.091405,0.002511,0.018969,0.005114,0.011716,-0.032731,-0.250877,-0.023433,-0.013297,0.109631,-0.037892,-0.040031,-0.015064,-0.069042,-0.077876,0.021573,-0.038775,-0.035846,-0.022782,0.181137,0.025385,0.016738,-0.040821,-0.040542,-0.092428,-0.027617,0.086012,-0.017946,-0.040728,-0.019062,0.002139,-0.092940,-0.004463,0.010507,0.082851,-0.019620,-0.079968,-0.007718,0.027338,-0.039054,-0.026780,0.175000,0.030685,-0.001906,-0.013251,-0.114838,-0.101262,-0.027152,-0.008183,0.029198,0.031429,-0.031243,0.149522,-0.018504,-0.035986,-0.108980,-0.012646,-0.001488,-0.024409\",\n" +
                "\t\t\t\"GXHTZ\": {\n" +
                "\t\t\t\t\"DCJQS\": \"0_80\",\n" +
                "\t\t\t\t\"CCZTW\": \"B_41_188,137,27,21#D_52_152,143,28,17#E_25_152,143,28,17\",\n" +
                "\t\t\t\t\"GJ\": \"\",\n" +
                "\t\t\t\t\"CSZT\": \"\",\n" +
                "\t\t\t\t\"XLJ\": \"\",\n" +
                "\t\t\t\t\"BJ\": \"\",\n" +
                "\t\t\t\t\"CSCH\": \"\",\n" +
                "\t\t\t\t\"CSPS\": \"\",\n" +
                "\t\t\t\t\"CSGH\": \"\",\n" +
                "\t\t\t\t\"TC\": \"\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"HPTZ\": {\n" +
                "\t\t\t\t\"YWLSHP\": \"0\",\n" +
                "\t\t\t\t\"HPZT\": \"1\",\n" +
                "\t\t\t\t\"HPYS\": \"B\",\n" +
                "\t\t\t\t\"HPKXD\": \"99\",\n" +
                "\t\t\t\t\"MWHPKXD\": \"贵-99,A-99,1-99,1-99,2-99,1-99,0-99\",\n" +
                "\t\t\t\t\"HPGG\": \"1\",\n" +
                "\t\t\t\t\"HPWZ\": \"89_241,551,158,48\",\n" +
                "\t\t\t\t\"HPZL\": \"02\",\n" +
                "\t\t\t\t\"HPHM\": \"贵A11210\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"JSXWTZ\": {\n" +
                "\t\t\t\t\"ZJSKSJ\": \"0_98\",\n" +
                "\t\t\t\t\"MTCBDTK\": \"\",\n" +
                "\t\t\t\t\"ZJSBJAQD\": \"0_97\",\n" +
                "\t\t\t\t\"ZJSDDH\": \"0_99\",\n" +
                "\t\t\t\t\"FJSZYB\": \"0_94\",\n" +
                "\t\t\t\t\"FJSBJAQD\": \"0_93\",\n" +
                "\t\t\t\t\"ZJSCY\": \"0_98\",\n" +
                "\t\t\t\t\"ZJSZYB\": \"0_97\"\n" +
                "\t\t\t}\n" +
                "\t\t}],\n" +
                "\t\t\"GCXH\": \"111\",\n" +
                "\t\t\"TimeStamp\": \"1060\",\n" +
                "\t\t\"ImageUrl\":\"www.baidu.com\"\n" +
                "\t}\n" +
                "}";
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
        System.out.println("aaaaaaaaaa");
        ResultRTO result = msg.getResult();
        List<VehicleInfoRTO> vehicleInfoList = result.getVehicleInfoList();

        String imageTableName = createService.createNewImageTable();
        ImageRTO image = this.generateImage(imageTableName,result,camera);
        //插入图片表
        insertService.insertImage(image);
        System.out.println("bbbbbbbbbbb");
        List<VehicleInformation> list = this.generateVehicleInformation(vehicleInfoList,image);
        VehicleInformationRTO vehicleInformationRTO = new VehicleInformationRTO();
        String vehicleInformationTableName = createService.createNewVehicleIfon();
        vehicleInformationRTO.setVehicleInformationList(list);
        vehicleInformationRTO.setVehicleInformationTableName(vehicleInformationTableName);
        insertService.insertVehicleInfo(vehicleInformationRTO);
        System.out.println("cccccccccccccccc");
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
