package com.zhuanjingkj.stpbe.tmdp.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.entity.TrafficViolationType;
import com.zhuanjingkj.stpbe.data.entity.VehicleJoinType;
import com.zhuanjingkj.stpbe.tmdp.dto.*;
import com.zhuanjingkj.stpbe.tmdp.dto.res.TrafficViolationListDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationStatisticDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.TrafficViolationRTO;
import com.zhuanjingkj.stpbe.tmdp.service.TrafficViolationService;
import com.zhuanjingkj.stpbe.tmdp.service.VehicleStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import java.util.ArrayList;
import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.21
 **/

@RestController
@RequestMapping("/traffic/violation")
@CrossOrigin(origins = "*")
@Validated
public class TrafficViolationController {

    @Autowired
    private TrafficViolationService trafficViolationService;

    @Autowired
    private VehicleStatisticService vehicleStatisticService;

    @GetMapping()
    public ResultDTO<Object> getTrafficViolationInfo() {
//        TrafficViolationListDTO trafficViolationListDTO = new TrafficViolationListDTO();

//        List<TrafficViolationStatisticDTO> trafficViolationTimeFrameNumberList = trafficViolationService.getTrafficViolationTimeFrameNumber();
////        List<TrafficViolationStatisticDTO> trafficViolationTypeNumberList = trafficViolationService.getTrafficViolationTypeNumber();
////        List<TrafficViolationDTO> trafficViolationList = trafficViolationService.getTrafficViolationByTime();
////        trafficViolationListDTO.setTrafficViolationTimeFrameNumberList(trafficViolationTimeFrameNumberList);
////        trafficViolationListDTO.setTrafficViolationTypeNumberList(trafficViolationTypeNumberList);
////        trafficViolationListDTO.setTrafficViolationList(trafficViolationList);
        
        String s = "{\n" +
                "\"trafficViolationTimeFrameNumberList\":[\n" +
                "{\n" +
                "\"trafficViolationNum\":20,\n" +
                "\"timeFrame\":2\n" +
                "},\n" +
                "{\n" +
                "\"trafficViolationNum\":100,\n" +
                "\"timeFrame\":4\n" +
                "},\n" +
                "{\n" +
                "\"trafficViolationNum\":1023,\n" +
                "\"timeFrame\":6\n" +
                "},\n" +
                "{\n" +
                "\"trafficViolationNum\":456,\n" +
                "\"timeFrame\":8\n" +
                "},\n" +
                "{\n" +
                "\"trafficViolationNum\":123,\n" +
                "\"timeFrame\":10\n" +
                "},\n" +
                "{\n" +
                "\"trafficViolationNum\":7865,\n" +
                "\"timeFrame\":12\n" +
                "},\n" +
                "{\n" +
                "\"trafficViolationNum\":442,\n" +
                "\"timeFrame\":14\n" +
                "},\n" +
                "{\n" +
                "\"trafficViolationNum\":789,\n" +
                "\"timeFrame\":16\n" +
                "},\n" +
                "{\n" +
                "\"trafficViolationNum\":112,\n" +
                "\"timeFrame\":18\n" +
                "},\n" +
                "{\n" +
                "\"trafficViolationNum\":785,\n" +
                "\"timeFrame\":20\n" +
                "},\n" +
                "{\n" +
                "\"trafficViolationNum\":23,\n" +
                "\"timeFrame\":22\n" +
                "},\n" +
                "{\n" +
                "\"trafficViolationNum\":7852,\n" +
                "\"timeFrame\":24\n" +
                "}\n" +
                "],\n" +
                "\"trafficViolationTypeNumberList\":[\n" +
                "{\n" +
                "\"trafficViolationType\":\"不带头盔\",\n" +
                "\"trafficViolationNum\":7987\n" +
                "},\n" +
                "{\n" +
                "\"trafficViolationType\":\"不系安全带\",\n" +
                "\"trafficViolationNum\":4654\n" +
                "}\n" +
                "],\n" +
                "\"trafficViolationList\":[\n" +
                "{\n" +
                "\"id\":3,\n" +
                "\"location\":\"天安门广场\",\n" +
                "\"vehicleType\":\"奔驰\",\n" +
                "\"vehicleStyle\":\"2020\",\n" +
                "\"violationTypeId\":3,\n" +
                "\"trafficViolationTime\":\"2020-11-24 18:55:15\",\n" +
                "\"imgId\":123,\n" +
                "\"vehiclePlate\":\"京A6666666\",\n" +
                "\"imgUrl\":\"http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg\",\n" +
                "\"violationTypeName\":\"不系安全带\"\n" +
                "},\n" +
                "{\n" +
                "\"id\":2,\n" +
                "\"location\":\"上地街十字\",\n" +
                "\"vehicleType\":\"帕萨特\",\n" +
                "\"vehicleStyle\":\"2020\",\n" +
                "\"violationTypeId\":2,\n" +
                "\"trafficViolationTime\":\"2020-11-24 18:54:05\",\n" +
                "\"imgId\":123,\n" +
                "\"vehiclePlate\":\"京A8888888\",\n" +
                "\"imgUrl\":\"http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg\",\n" +
                "\"violationTypeName\":\"不带头盔\"\n" +
                "}\n" +
                "]\n" +
                "}";
        Object j = JSON.parse(s);
        return ResultDTO.success(j);
    }
    @PostMapping("/list")
    public ResultDTO<PageInfo> getTrafficViolationList(@RequestBody TrafficViolationRTO trafficViolationRTO) {
        PageInfo<TrafficViolationDTO> pageInfo = trafficViolationService.getTrafficViolation(trafficViolationRTO);
        return ResultDTO.success(pageInfo);
    }


    @GetMapping("/vehicle/join/type")
    public ResultDTO<List> getVehicleJoinType() {
        List<VehicleJoinType> list = vehicleStatisticService.getVehicleJoinType();
        return ResultDTO.success(list);
    }

    @GetMapping("/type")
    public ResultDTO<List> getTrafficViolationType() {
        List<TrafficViolationType> list = trafficViolationService.getTrafficViolationType();
        return ResultDTO.success(list);
    }

    @GetMapping("/detail")
    public ResultDTO<TrafficViolationDetailDTO> getTrafficViolationDetail(@NotNull(message = "违章ID不能为空") Integer id) {
        TrafficViolationDetailDTO trafficViolationDetail = new TrafficViolationDetailDTO();
        trafficViolationDetail.setDate("2020-12-08 12:00:00");
        trafficViolationDetail.setIsNative("0");
        trafficViolationDetail.setLocation("上地六街十字");
        trafficViolationDetail.setVehicleColor("白色");
        trafficViolationDetail.setTrafficViolationType("不系安全带");
        trafficViolationDetail.setVehiclePlate("京A12345");
        trafficViolationDetail.setVehicleSubType("轿车");
        trafficViolationDetail.setVehicleType("小型客车");

        DrivingBehaviorDTO drivingBehavior = new DrivingBehaviorDTO();
        drivingBehavior.setCopilotDownSunShield(0);
        drivingBehavior.setCopilotWithoutSeatBelt(0);
        drivingBehavior.setDriverCalling(0);
        drivingBehavior.setDriverSmoking(0);
        drivingBehavior.setWithoutHelmet(0);
        drivingBehavior.setDriverReadPhone(0);
        drivingBehavior.setDriverWithoutSeatBelt(1);
        drivingBehavior.setDriverDownSunShield(0);
        VehicleBrandDTO vehicleBrand = new VehicleBrandDTO();
        vehicleBrand.setCredibility("98");
        vehicleBrand.setVehicleBrand("大众");
        vehicleBrand.setVehicleType("帕萨特");
        vehicleBrand.setVehicleYear("2020");

        LicencePlateDTO licencePlate = new LicencePlateDTO();
        licencePlate.setEachLicencePlateReliability("京-99,A-99,1-99,2-99,3-99,4-99,5-99");
        licencePlate.setLicencePlatColorStyle("蓝色");
        licencePlate.setLicencePlateCharSetStyle("单排单列");
        licencePlate.setLicencePlateNumber("京A12345");
        licencePlate.setLicencePlateStatus("正常");
        licencePlate.setLicencePlateType("小型汽车");
        licencePlate.setTempLicencePlate(0);
        licencePlate.setLicencePlateReliability("98");
        trafficViolationDetail.setDrivingBehavior(drivingBehavior);
        trafficViolationDetail.setVehicleBrand(vehicleBrand);
        trafficViolationDetail.setLicencePlate(licencePlate);
        return ResultDTO.success(trafficViolationDetail);
    }

    @GetMapping("/history")
    public ResultDTO<TrafficViolationHistory> getTrafficViolationHistory(@NotNull(message = "车辆号牌不能为空") String licensePlate) {
        TrafficViolationHistory trafficViolationHistory = new TrafficViolationHistory();
        trafficViolationHistory.setCurrentYearTrafficViolationNum(20);
        trafficViolationHistory.setLicensePlate("京A12345");
        trafficViolationHistory.setTotalTrafficViolationNum(100);
        List<TrafficViolationTypeDTO> trafficViolationTypeList = new ArrayList<>();
        List<TrafficViolationHistoryNumDTO> trafficViolationNumList = new ArrayList<>();

        trafficViolationHistory.setTrafficViolationHistoryNumList(trafficViolationNumList);
        trafficViolationHistory.setTrafficViolationTypeList(trafficViolationTypeList);

        TrafficViolationTypeDTO  trafficViolationType1 = new TrafficViolationTypeDTO();
        trafficViolationType1.setTrafficViolationType("主驾驶不系安全带");
        trafficViolationType1.setTrafficViolationNum(40);

        TrafficViolationTypeDTO  trafficViolationType2 = new TrafficViolationTypeDTO();
        trafficViolationType2.setTrafficViolationType("闯红灯");
        trafficViolationType2.setTrafficViolationNum(34);

        TrafficViolationTypeDTO  trafficViolationType3 = new TrafficViolationTypeDTO();
        trafficViolationType3.setTrafficViolationType("主驾驶抽烟");
        trafficViolationType3.setTrafficViolationNum(30);

        TrafficViolationTypeDTO  trafficViolationType4 = new TrafficViolationTypeDTO();
        trafficViolationType4.setTrafficViolationType("逆行");
        trafficViolationType4.setTrafficViolationNum(20);

        TrafficViolationTypeDTO  trafficViolationType5 = new TrafficViolationTypeDTO();
        trafficViolationType5.setTrafficViolationType("压线");
        trafficViolationType5.setTrafficViolationNum(15);

        trafficViolationTypeList.add(trafficViolationType1);
        trafficViolationTypeList.add(trafficViolationType2);
        trafficViolationTypeList.add(trafficViolationType3);
        trafficViolationTypeList.add(trafficViolationType4);
        trafficViolationTypeList.add(trafficViolationType5);

        TrafficViolationHistoryNumDTO trafficViolationHistoryNum1 = new TrafficViolationHistoryNumDTO();
        trafficViolationHistoryNum1.setNum(11);
        trafficViolationHistoryNum1.setYear("2016");

        TrafficViolationHistoryNumDTO trafficViolationHistoryNum2 = new TrafficViolationHistoryNumDTO();
        trafficViolationHistoryNum2.setNum(15);
        trafficViolationHistoryNum2.setYear("2017");

        TrafficViolationHistoryNumDTO trafficViolationHistoryNum3 = new TrafficViolationHistoryNumDTO();
        trafficViolationHistoryNum3.setNum(13);
        trafficViolationHistoryNum3.setYear("2018");

        TrafficViolationHistoryNumDTO trafficViolationHistoryNum4 = new TrafficViolationHistoryNumDTO();
        trafficViolationHistoryNum4.setNum(8);
        trafficViolationHistoryNum4.setYear("2019");

        trafficViolationNumList.add(trafficViolationHistoryNum1);
        trafficViolationNumList.add(trafficViolationHistoryNum2);
        trafficViolationNumList.add(trafficViolationHistoryNum3);
        trafficViolationNumList.add(trafficViolationHistoryNum4);
        return ResultDTO.success(trafficViolationHistory);
    }

    @PostMapping("history/list")
    public ResultDTO<PageInfo> getTrafficViolationHistoryList(@RequestBody TrafficViolationRTO trafficViolationRTO) {
        PageInfo<TrafficViolationDTO> pageInfo = trafficViolationService.getTrafficViolation(trafficViolationRTO);
        return ResultDTO.success(pageInfo);
    }
}
