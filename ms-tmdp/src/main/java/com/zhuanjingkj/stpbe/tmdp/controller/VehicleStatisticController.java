package com.zhuanjingkj.stpbe.tmdp.controller;

import com.alibaba.fastjson.JSON;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.res.VehicleStatisticListDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.*;
import com.zhuanjingkj.stpbe.tmdp.service.VehicleStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.20
 **/

@RestController
@RequestMapping("/vehicle/statistic")
@CrossOrigin(origins = "*")
public class VehicleStatisticController {

    @Autowired
    private VehicleStatisticService vehicleStatisticService;

    @GetMapping()
    public ResultDTO<Object> getVehicleStatisticInfo() {
//        List<VehicleDistributionDTO> vehicleDistributionList = vehicleStatisticService.getVehicleDistribution();
//
//        List<VehicleTypeDTO> vehicleTypeList = vehicleStatisticService.getVehicleType();
//
//        List<TimeFrameNumberDTO> timeFrameNumberList = vehicleStatisticService.getTimeFrameNumber();
//
//        List<VehicleTypeNumberDTO> vehicleTypeNumberList = vehicleStatisticService.getVehicleTypeNumber();
//
//        List<VehicleStatisticDTO> streetList = vehicleStatisticService.getVehicleStatisticByStreet();
//
//        List<VehicleStatisticDTO> regionList = vehicleStatisticService.getVehicleStatisticByRegion();
//
//        VehiclePassedNumberDTO vehiclePassedNumber = vehicleStatisticService.getVehiclePassedNumber();
//
//        VehicleStatisticListDTO vehicleDistributionListDTO = new VehicleStatisticListDTO();
//        vehicleDistributionListDTO.setVehicleDistributionList(vehicleDistributionList);
//        vehicleDistributionListDTO.setVehicleTypeList(vehicleTypeList);
//        vehicleDistributionListDTO.setTimeFrameNumberList(timeFrameNumberList);
//        vehicleDistributionListDTO.setVehicleTypeNumberList(vehicleTypeNumberList);
//        vehicleDistributionListDTO.setStreetList(streetList);
//        vehicleDistributionListDTO.setRegionList(regionList);
//        vehicleDistributionListDTO.setVehiclePassedNumber(vehiclePassedNumber);

        String s = "{\n" +
                "\t\"vehicleDistributionList\": [{\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"vehicleDistributionName\":  \"本市车辆\",\n" +
                "\t\t\"vehicleDistributionPercntage\":  \"70%\"\n" +
                "\t},  {\n" +
                "\t\t\"id\": 2,\n" +
                "\t\t\"vehicleDistributionName\":  \"外埠\",\n" +
                "\t\t\"vehicleDistributionPercntage\":  \"30%\"\n" +
                "\t}],\n" +
                "\t\"timeFrameNumberList\": [{\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"timeFrame\": 4,\n" +
                "\t\t\"date\":  \"2020-11-2611:51:55\",\n" +
                "\t\t\"vehicleNumber\": 82456666\n" +
                "\t},  {\n" +
                "\t\t\"id\": 2,\n" +
                "\t\t\"timeFrame\": 8,\n" +
                "\t\t\"date\":  \"2020-11-2611:53:50\",\n" +
                "\t\t\"vehicleNumber\": 65465465\n" +
                "\t},  {\n" +
                "\t\t\"id\": 3,\n" +
                "\t\t\"timeFrame\": 12,\n" +
                "\t\t\"date\":  \"2020-11-2611:54:01\",\n" +
                "\t\t\"vehicleNumber\": 465465465\n" +
                "\t},  {\n" +
                "\t\t\"id\": 4,\n" +
                "\t\t\"timeFrame\": 16,\n" +
                "\t\t\"date\":  \"2020-11-2611:54:11\",\n" +
                "\t\t\"vehicleNumber\": 35165465\n" +
                "\t},  {\n" +
                "\t\t\"id\": 5,\n" +
                "\t\t\"timeFrame\": 20,\n" +
                "\t\t\"date\":  \"2020-11-2611:54:32\",\n" +
                "\t\t\"vehicleNumber\": 4685465\n" +
                "\t},  {\n" +
                "\t\t\"id\": 6,\n" +
                "\t\t\"timeFrame\": 24,\n" +
                "\t\t\"date\":  \"2020-11-2611:54:49\",\n" +
                "\t\t\"vehicleNumber\": 98798798\n" +
                "\t},  {\n" +
                "\t\t\"id\": 7,\n" +
                "\t\t\"timeFrame\": 4,\n" +
                "\t\t\"date\":  \"2020-11-2511:55:02\",\n" +
                "\t\t\"vehicleNumber\": 4654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 8,\n" +
                "\t\t\"timeFrame\": 8,\n" +
                "\t\t\"date\":  \"2020-11-2511:55:15\",\n" +
                "\t\t\"vehicleNumber\": 65465465\n" +
                "\t},  {\n" +
                "\t\t\"id\": 9,\n" +
                "\t\t\"timeFrame\": 12,\n" +
                "\t\t\"date\":  \"2020-11-2511:55:27\",\n" +
                "\t\t\"vehicleNumber\": 465456\n" +
                "\t},  {\n" +
                "\t\t\"id\": 10,\n" +
                "\t\t\"timeFrame\": 16,\n" +
                "\t\t\"date\":  \"2020-11-2511:55:37\",\n" +
                "\t\t\"vehicleNumber\": 6846854\n" +
                "\t},  {\n" +
                "\t\t\"id\": 11,\n" +
                "\t\t\"timeFrame\": 20,\n" +
                "\t\t\"date\":  \"2020-11-2511:55:48\",\n" +
                "\t\t\"vehicleNumber\": 564654\n" +
                "\t}],\n" +
                "\t\"vehicleTypeNumberList\": [{\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"vehicleTypeName\":  \"小型车\",\n" +
                "\t\t\"timeFrame\": 2,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 2,\n" +
                "\t\t\"vehicleTypeName\":  \"小型车\",\n" +
                "\t\t\"timeFrame\": 4,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 3,\n" +
                "\t\t\"vehicleTypeName\":  \"小型车\",\n" +
                "\t\t\"timeFrame\": 6,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 4,\n" +
                "\t\t\"vehicleTypeName\":  \"小型车\",\n" +
                "\t\t\"timeFrame\": 8,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 5,\n" +
                "\t\t\"vehicleTypeName\":  \"小型车\",\n" +
                "\t\t\"timeFrame\": 10,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 6,\n" +
                "\t\t\"vehicleTypeName\":  \"小型车\",\n" +
                "\t\t\"timeFrame\": 12,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 7,\n" +
                "\t\t\"vehicleTypeName\":  \"小型车\",\n" +
                "\t\t\"timeFrame\": 14,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 8,\n" +
                "\t\t\"vehicleTypeName\":  \"小型车\",\n" +
                "\t\t\"timeFrame\": 16,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 9,\n" +
                "\t\t\"vehicleTypeName\":  \"小型车\",\n" +
                "\t\t\"timeFrame\": 18,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 10,\n" +
                "\t\t\"vehicleTypeName\":  \"小型车\",\n" +
                "\t\t\"timeFrame\": 20,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 11,\n" +
                "\t\t\"vehicleTypeName\":  \"小型车\",\n" +
                "\t\t\"timeFrame\": 22,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 12,\n" +
                "\t\t\"vehicleTypeName\":  \"小型车\",\n" +
                "\t\t\"timeFrame\": 24,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 13,\n" +
                "\t\t\"vehicleTypeName\":  \"中型车\",\n" +
                "\t\t\"timeFrame\": 2,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 14,\n" +
                "\t\t\"vehicleTypeName\":  \"中型车\",\n" +
                "\t\t\"timeFrame\": 4,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 15,\n" +
                "\t\t\"vehicleTypeName\":  \"中型车\",\n" +
                "\t\t\"timeFrame\": 6,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 16,\n" +
                "\t\t\"vehicleTypeName\":  \"中型车\",\n" +
                "\t\t\"timeFrame\": 8,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 17,\n" +
                "\t\t\"vehicleTypeName\":  \"中型车\",\n" +
                "\t\t\"timeFrame\": 10,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 18,\n" +
                "\t\t\"vehicleTypeName\":  \"中型车\",\n" +
                "\t\t\"timeFrame\": 12,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 19,\n" +
                "\t\t\"vehicleTypeName\":  \"中型车\",\n" +
                "\t\t\"timeFrame\": 14,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 20,\n" +
                "\t\t\"vehicleTypeName\":  \"中型车\",\n" +
                "\t\t\"timeFrame\": 16,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 21,\n" +
                "\t\t\"vehicleTypeName\":  \"中型车\",\n" +
                "\t\t\"timeFrame\": 18,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 22,\n" +
                "\t\t\"vehicleTypeName\":  \"中型车\",\n" +
                "\t\t\"timeFrame\": 20,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 23,\n" +
                "\t\t\"vehicleTypeName\":  \"中型车\",\n" +
                "\t\t\"timeFrame\": 22,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 24,\n" +
                "\t\t\"vehicleTypeName\":  \"中型车\",\n" +
                "\t\t\"timeFrame\": 24,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 25,\n" +
                "\t\t\"vehicleTypeName\":  \"大型车\",\n" +
                "\t\t\"timeFrame\": 2,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 26,\n" +
                "\t\t\"vehicleTypeName\":  \"大型车\",\n" +
                "\t\t\"timeFrame\": 4,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 27,\n" +
                "\t\t\"vehicleTypeName\":  \"大型车\",\n" +
                "\t\t\"timeFrame\": 6,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 28,\n" +
                "\t\t\"vehicleTypeName\":  \"大型车\",\n" +
                "\t\t\"timeFrame\": 8,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 29,\n" +
                "\t\t\"vehicleTypeName\":  \"大型车\",\n" +
                "\t\t\"timeFrame\": 10,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 30,\n" +
                "\t\t\"vehicleTypeName\":  \"大型车\",\n" +
                "\t\t\"timeFrame\": 12,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 31,\n" +
                "\t\t\"vehicleTypeName\":  \"大型车\",\n" +
                "\t\t\"timeFrame\": 14,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 32,\n" +
                "\t\t\"vehicleTypeName\":  \"大型车\",\n" +
                "\t\t\"timeFrame\": 16,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 33,\n" +
                "\t\t\"vehicleTypeName\":  \"大型车\",\n" +
                "\t\t\"timeFrame\": 18,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 34,\n" +
                "\t\t\"vehicleTypeName\":  \"大型车\",\n" +
                "\t\t\"timeFrame\": 20,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 35,\n" +
                "\t\t\"vehicleTypeName\":  \"大型车\",\n" +
                "\t\t\"timeFrame\": 22,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t},  {\n" +
                "\t\t\"id\": 36,\n" +
                "\t\t\"vehicleTypeName\":  \"大型车\",\n" +
                "\t\t\"timeFrame\": 24,\n" +
                "\t\t\"vehicleNumber\": 654654\n" +
                "\t}],\n" +
                "\t\"streetList\": [{\n" +
                "\t\t\"streetName\":  \"上地街十字\",\n" +
                "\t\t\"passedNumber\": 654654\n" +
                "\t}],\n" +
                "\t\"regionList\": [{\n" +
                "\t\t\"regionName\":  \"海淀\",\n" +
                "\t\t\"passedNumber\": 654654\n" +
                "\t}],\n" +
                "\t\"vehiclePassedNumber\": {\n" +
                "\t\t\"todayPassedNumber\": 5556,\n" +
                "\t\t\"sevenDayPassedNumber\": 5556,\n" +
                "\t\t\"currentMonthPassedNumber\": 5556,\n" +
                "\t\t\"dailyMean\": 213\n" +
                "\t},\n" +
                "\t\"vehicleTypeDTOList\": [{\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"vehicleTypeName\":  \"小汽车\",\n" +
                "\t\t\"vehicleTypePercentage\":  \"41%\"\n" +
                "\t},  {\n" +
                "\t\t\"id\": 2,\n" +
                "\t\t\"vehicleTypeName\":  \"SUV\",\n" +
                "\t\t\"vehicleTypePercentage\":  \"23%\"\n" +
                "\t},  {\n" +
                "\t\t\"id\": 3,\n" +
                "\t\t\"vehicleTypeName\":  \"MPV\",\n" +
                "\t\t\"vehicleTypePercentage\":  \"11%\"\n" +
                "\t},  {\n" +
                "\t\t\"id\": 4,\n" +
                "\t\t\"vehicleTypeName\":  \"面包车\",\n" +
                "\t\t\"vehicleTypePercentage\":  \"6%\"\n" +
                "\t},  {\n" +
                "\t\t\"id\": 5,\n" +
                "\t\t\"vehicleTypeName\":  \"罐式货车\",\n" +
                "\t\t\"vehicleTypePercentage\":  \"9%\"\n" +
                "\t},  {\n" +
                "\t\t\"id\": 6,\n" +
                "\t\t\"vehicleTypeName\":  \"普通货车\",\n" +
                "\t\t\"vehicleTypePercentage\":  \"10%\"\n" +
                "\t}]\n" +
                "}";
        Object j = JSON.parse(s);
        return ResultDTO.success(j);
    }
}
