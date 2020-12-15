package com.zhuanjingkj.stpbe.tmdp.controller;

import com.alibaba.fastjson.JSON;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.emphavehi.EmphasisVehicleImgUrl;
import com.zhuanjingkj.stpbe.tmdp.dto.emphavehi.EmphasisVehicleInformationDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.emphavehi.EmphasisVehicleNumberDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.emphavehi.EmphasisVehicleTimeFrameDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.res.EmphasisVehicleListDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationDTO;
import com.zhuanjingkj.stpbe.tmdp.service.EmphasisVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.24
 **/

@RestController
@RequestMapping("/emphasis/vehicle")
@CrossOrigin(origins = "*")
public class EmphasisVehicleController {

    @Autowired
    private EmphasisVehicleService emphasisVehicleService;

    @GetMapping()
    public ResultDTO<Object> getEmphasisVehicleInfo() {
//        EmphasisVehicleListDTO emphasisVehicleListDTO = new EmphasisVehicleListDTO();
//
//        List<EmphasisVehicleInformationDTO> emphasisVehiclePercentageList = emphasisVehicleService.getEmphasisVehiclePercentage();
//        List<EmphasisVehicleInformationDTO> regionEmphasisVehicleList = emphasisVehicleService.getEmphasisVehicleByRegion();
//        List<EmphasisVehicleInformationDTO> siteEmphasisVehicleList = emphasisVehicleService.getEmphasisVehicleBySite();
//        List<TrafficViolationDTO> emphasisVehicleViolationList = emphasisVehicleService.getEmphasisVehicleViolation();
//        List<EmphasisVehicleTimeFrameDTO> emphasisVehicleTimeFrameList = emphasisVehicleService.getEmphasisVehicleByTimeFrame();
//        EmphasisVehicleNumberDTO emphasisVehicleNumber = emphasisVehicleService.getEmphasisVehicleNumber();
//        List<EmphasisVehicleImgUrl> emphasisVehicleImgUrList = emphasisVehicleService.getEmphasisVehicleImg();
//        emphasisVehicleListDTO.setEmphasisVehiclePercentageList(emphasisVehiclePercentageList);
//        emphasisVehicleListDTO.setRegionEmphasisVehicleList(regionEmphasisVehicleList);
//        emphasisVehicleListDTO.setSiteEmphasisVehicleList(siteEmphasisVehicleList);
//        emphasisVehicleListDTO.setEmphasisVehicleViolationList(emphasisVehicleViolationList);
//        emphasisVehicleListDTO.setEmphasisVehicleTimeFrameList(emphasisVehicleTimeFrameList);
//        emphasisVehicleListDTO.setEmphasisVehicleNumber(emphasisVehicleNumber);
//        emphasisVehicleListDTO.setEmphasisVehicleImgUrlList(emphasisVehicleImgUrList);
        String s = "{\n" +
                "\"emphasisVehiclePercentageList\":[\n" +
                "{\n" +
                "\"emphasisVehicleTypeName\":\"仓栅式货车*\",\n" +
                "\"emphasisVehicleNum\":600,\n" +
                "\"percentage\":\"0.9997\"\n" +
                "},\n" +
                "{\n" +
                "\"emphasisVehicleTypeName\":\"平板式货车#\",\n" +
                "\"emphasisVehicleNum\":123,\n" +
                "\"percentage\":\"0.0000\"\n" +
                "},\n" +
                "{\n" +
                "\"emphasisVehicleTypeName\":\"栏板试货车\",\n" +
                "\"emphasisVehicleNum\":789,\n" +
                "\"percentage\":\"0.0002\"\n" +
                "},\n" +
                "{\n" +
                "\"emphasisVehicleTypeName\":\"箱式货车\",\n" +
                "\"emphasisVehicleNum\":123,\n" +
                "\"percentage\":\"0.0000\"\n" +
                "},\n" +
                "{\n" +
                "\"emphasisVehicleTypeName\":\"罐式货车\",\n" +
                "\"emphasisVehicleNum\":456,\n" +
                "\"percentage\":\"0.0001\"\n" +
                "}\n" +
                "],\n" +
                "\"regionEmphasisVehicleList\":[\n" +
                "{\n" +
                "\"emphasisVehicleNum\":4654654,\n" +
                "\"regionName\":\"西城区\"\n" +
                "},\n" +
                "{\n" +
                "\"emphasisVehicleNum\":789,\n" +
                "\"regionName\":\"通州\"\n" +
                "},\n" +
                "{\n" +
                "\"emphasisVehicleNum\":456,\n" +
                "\"regionName\":\"昌平\"\n" +
                "},\n" +
                "{\n" +
                "\"emphasisVehicleNum\":123,\n" +
                "\"regionName\":\"海淀\"\n" +
                "},\n" +
                "{\n" +
                "\"emphasisVehicleNum\":123,\n" +
                "\"regionName\":\"东区\"\n" +
                "}\n" +
                "],\n" +
                "\"siteEmphasisVehicleList\":[\n" +
                "{\n" +
                "\"emphasisVehicleNum\":4655566,\n" +
                "\"siteId\":2,\n" +
                "\"siteName\":\"上地五街十字\"\n" +
                "},\n" +
                "{\n" +
                "\"emphasisVehicleNum\":579,\n" +
                "\"siteId\":1,\n" +
                "\"siteName\":\"上地六街十字\"\n" +
                "}\n" +
                "],\n" +
                "\"emphasisVehicleViolationList\":[\n" +
                "{\n" +
                "\"id\":3,\n" +
                "\"location\":\"天安门广场\",\n" +
                "\"vehicleType\":\"奔驰\",\n" +
                "\"vehicleStyle\":\"2020\",\n" +
                "\"violationTypeId\":3,\n" +
                "\"trafficViolationTime\":\"2020-11-2418:55:15\",\n" +
                "\"imgVaId\":123,\n" +
                "\"vehiclePlate\":\"京A6666666\",\n" +
                "\"imgUrl\":\"http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg\",\n" +
                "\"violationTypeName\":\"不系安全带\",\n" +
                "\"siteName\":\"上地五街十字\",\n" +
                "\"lng\":\"192.167\",\n" +
                "\"lat\":\"192.123\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":2,\n" +
                "\"location\":\"上地街十字\",\n" +
                "\"vehicleType\":\"帕萨特\",\n" +
                "\"vehicleStyle\":\"2020\",\n" +
                "\"violationTypeId\":2,\n" +
                "\"trafficViolationTime\":\"2020-11-2418:54:05\",\n" +
                "\"imgVaId\":123,\n" +
                "\"vehiclePlate\":\"京A8888888\",\n" +
                "\"imgUrl\":\"http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg\",\n" +
                "\"violationTypeName\":\"不带头盔\",\n" +
                "\"siteName\":\"上地六街十字\",\n" +
                "\"lng\":\"192.168\",\n" +
                "\"lat\":\"192.169\"\n" +
                "}\n" +
                "],\n" +
                "\"emphasisVehicleNumber\":{\n" +
                "\"currentEmphasisVehicleNum\":4656145,\n" +
                "\"alarmNum\":0,\n" +
                "\"emphasisVehicleEmphasisRegionNum\":123,\n" +
                "\"onlineDevice\":3\n" +
                "},\n" +
                "\"emphasisVehicleImgUrlList\":[\n" +
                "{\n" +
                "\"id\":13,\n" +
                "\"imgUrl\":\"http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":14,\n" +
                "\"imgUrl\":\"http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg\"\n" +
                "}\n" +
                "],\n" +
                "\"emphasisVehicleTimeFrameList\":[\n" +
                "{\n" +
                "\"date\":\"2020-11-26\",\n" +
                "\"timeFrame\":2\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-26\",\n" +
                "\"timeFrame\":4\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-26\",\n" +
                "\"timeFrame\":6\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-26\",\n" +
                "\"timeFrame\":8\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-26\",\n" +
                "\"timeFrame\":10\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-26\",\n" +
                "\"timeFrame\":12\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-26\",\n" +
                "\"emphasisVehicleNum\":4656145,\n" +
                "\"timeFrame\":14\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-26\",\n" +
                "\"timeFrame\":16\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-26\",\n" +
                "\"timeFrame\":18\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-26\",\n" +
                "\"timeFrame\":20\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-26\",\n" +
                "\"timeFrame\":22\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-26\",\n" +
                "\"timeFrame\":24\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-25\",\n" +
                "\"timeFrame\":2\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-25\",\n" +
                "\"timeFrame\":4\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-25\",\n" +
                "\"timeFrame\":6\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-25\",\n" +
                "\"timeFrame\":8\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-25\",\n" +
                "\"timeFrame\":10\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-25\",\n" +
                "\"timeFrame\":12\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-25\",\n" +
                "\"emphasisVehicleNum\":4656145,\n" +
                "\"timeFrame\":14\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-25\",\n" +
                "\"timeFrame\":16\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-25\",\n" +
                "\"timeFrame\":18\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-25\",\n" +
                "\"timeFrame\":20\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-25\",\n" +
                "\"timeFrame\":22\n" +
                "},\n" +
                "{\n" +
                "\"date\":\"2020-11-25\",\n" +
                "\"timeFrame\":24\n" +
                "}\n" +
                "]\n" +
                "}";
        Object j = JSON.parse(s);
        return ResultDTO.success(j);
    }
}
