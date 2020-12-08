package com.zhuanjingkj.stpbe.tmdp.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.entity.TrafficViolationType;
import com.zhuanjingkj.stpbe.data.entity.VehicleJoinType;
import com.zhuanjingkj.stpbe.tmdp.dto.res.TrafficViolationListDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationStatisticDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.TrafficViolationRTO;
import com.zhuanjingkj.stpbe.tmdp.service.TrafficViolationService;
import com.zhuanjingkj.stpbe.tmdp.service.VehicleStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;
import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.21
 **/

@RestController
@RequestMapping("/traffic/violation")
@CrossOrigin(origins = "*")
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
}
