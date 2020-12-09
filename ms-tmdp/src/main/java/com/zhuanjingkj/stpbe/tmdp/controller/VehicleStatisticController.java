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
                "        \"vehiclePassedNumber\": {\n" +
                "            \"todayPassedNumber\": 5556,\n" +
                "            \"dailyMean\": 7878,\n" +
                "            \"currentMonthPassedNumber\": 6786786,\n" +
                "            \"sevenDayPassedNumber\": 4545645\n" +
                "        },\n" +
                "        \"vehicleTypeNumberList\": [\n" +
                "            {\n" +
                "                \"vehicleNumber\": 545455,\n" +
                "                \"id\": 1,\n" +
                "                \"vehicleTypeName\": \"小型车\",\n" +
                "                \"timeFrame\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 32132,\n" +
                "                \"id\": 2,\n" +
                "                \"vehicleTypeName\": \"小型车\",\n" +
                "                \"timeFrame\": 4\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 65423,\n" +
                "                \"id\": 3,\n" +
                "                \"vehicleTypeName\": \"小型车\",\n" +
                "                \"timeFrame\": 6\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 894654,\n" +
                "                \"id\": 4,\n" +
                "                \"vehicleTypeName\": \"小型车\",\n" +
                "                \"timeFrame\": 8\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 321232,\n" +
                "                \"id\": 5,\n" +
                "                \"vehicleTypeName\": \"小型车\",\n" +
                "                \"timeFrame\": 10\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 326545,\n" +
                "                \"id\": 6,\n" +
                "                \"vehicleTypeName\": \"小型车\",\n" +
                "                \"timeFrame\": 12\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 654654,\n" +
                "                \"id\": 7,\n" +
                "                \"vehicleTypeName\": \"小型车\",\n" +
                "                \"timeFrame\": 14\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 533245,\n" +
                "                \"id\": 8,\n" +
                "                \"vehicleTypeName\": \"小型车\",\n" +
                "                \"timeFrame\": 16\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 895451,\n" +
                "                \"id\": 9,\n" +
                "                \"vehicleTypeName\": \"小型车\",\n" +
                "                \"timeFrame\": 18\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 11655,\n" +
                "                \"id\": 10,\n" +
                "                \"vehicleTypeName\": \"小型车\",\n" +
                "                \"timeFrame\": 20\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 65323,\n" +
                "                \"id\": 11,\n" +
                "                \"vehicleTypeName\": \"小型车\",\n" +
                "                \"timeFrame\": 22\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 3254,\n" +
                "                \"id\": 12,\n" +
                "                \"vehicleTypeName\": \"小型车\",\n" +
                "                \"timeFrame\": 24\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 564654,\n" +
                "                \"id\": 13,\n" +
                "                \"vehicleTypeName\": \"中型车\",\n" +
                "                \"timeFrame\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 5465465,\n" +
                "                \"id\": 14,\n" +
                "                \"vehicleTypeName\": \"中型车\",\n" +
                "                \"timeFrame\": 4\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 545458,\n" +
                "                \"id\": 15,\n" +
                "                \"vehicleTypeName\": \"中型车\",\n" +
                "                \"timeFrame\": 6\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 654654,\n" +
                "                \"id\": 16,\n" +
                "                \"vehicleTypeName\": \"中型车\",\n" +
                "                \"timeFrame\": 8\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 132345,\n" +
                "                \"id\": 17,\n" +
                "                \"vehicleTypeName\": \"中型车\",\n" +
                "                \"timeFrame\": 10\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 10042,\n" +
                "                \"id\": 18,\n" +
                "                \"vehicleTypeName\": \"中型车\",\n" +
                "                \"timeFrame\": 12\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 44545,\n" +
                "                \"id\": 19,\n" +
                "                \"vehicleTypeName\": \"中型车\",\n" +
                "                \"timeFrame\": 14\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 789786,\n" +
                "                \"id\": 20,\n" +
                "                \"vehicleTypeName\": \"中型车\",\n" +
                "                \"timeFrame\": 16\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 54656,\n" +
                "                \"id\": 21,\n" +
                "                \"vehicleTypeName\": \"中型车\",\n" +
                "                \"timeFrame\": 18\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 78978,\n" +
                "                \"id\": 22,\n" +
                "                \"vehicleTypeName\": \"中型车\",\n" +
                "                \"timeFrame\": 20\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 567456,\n" +
                "                \"id\": 23,\n" +
                "                \"vehicleTypeName\": \"中型车\",\n" +
                "                \"timeFrame\": 22\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 879789,\n" +
                "                \"id\": 24,\n" +
                "                \"vehicleTypeName\": \"中型车\",\n" +
                "                \"timeFrame\": 24\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 734534,\n" +
                "                \"id\": 25,\n" +
                "                \"vehicleTypeName\": \"大型车\",\n" +
                "                \"timeFrame\": 2\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 456456,\n" +
                "                \"id\": 26,\n" +
                "                \"vehicleTypeName\": \"大型车\",\n" +
                "                \"timeFrame\": 4\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 456456,\n" +
                "                \"id\": 27,\n" +
                "                \"vehicleTypeName\": \"大型车\",\n" +
                "                \"timeFrame\": 6\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 456456,\n" +
                "                \"id\": 28,\n" +
                "                \"vehicleTypeName\": \"大型车\",\n" +
                "                \"timeFrame\": 8\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 65789,\n" +
                "                \"id\": 29,\n" +
                "                \"vehicleTypeName\": \"大型车\",\n" +
                "                \"timeFrame\": 10\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 100245,\n" +
                "                \"id\": 30,\n" +
                "                \"vehicleTypeName\": \"大型车\",\n" +
                "                \"timeFrame\": 12\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 102030,\n" +
                "                \"id\": 31,\n" +
                "                \"vehicleTypeName\": \"大型车\",\n" +
                "                \"timeFrame\": 14\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 1000,\n" +
                "                \"id\": 32,\n" +
                "                \"vehicleTypeName\": \"大型车\",\n" +
                "                \"timeFrame\": 16\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 45454,\n" +
                "                \"id\": 33,\n" +
                "                \"vehicleTypeName\": \"大型车\",\n" +
                "                \"timeFrame\": 18\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 456456,\n" +
                "                \"id\": 34,\n" +
                "                \"vehicleTypeName\": \"大型车\",\n" +
                "                \"timeFrame\": 20\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 7865456,\n" +
                "                \"id\": 35,\n" +
                "                \"vehicleTypeName\": \"大型车\",\n" +
                "                \"timeFrame\": 22\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleNumber\": 76456,\n" +
                "                \"id\": 36,\n" +
                "                \"vehicleTypeName\": \"大型车\",\n" +
                "                \"timeFrame\": 24\n" +
                "            }\n" +
                "        ],\n" +
                "        \"regionList\": [\n" +
                "            {\n" +
                "                \"passedNumber\": 456546,\n" +
                "                \"regionName\": \"海淀区\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"passedNumber\": 456546,\n" +
                "                \"regionName\": \"东城区\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"passedNumber\": 456546,\n" +
                "                \"regionName\": \"西城区\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"passedNumber\": 456546,\n" +
                "                \"regionName\": \"朝阳区\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"passedNumber\": 456546,\n" +
                "                \"regionName\": \"通州区\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"timeFrameNumberList\": [\n" +
                "            {\n" +
                "                \"date\": \"2020-11-26 11:51:55\",\n" +
                "                \"vehicleNumber\": 82456666,\n" +
                "                \"id\": 1,\n" +
                "                \"timeFrame\": 4\n" +
                "            },\n" +
                "            {\n" +
                "                \"date\": \"2020-11-26 11:53:50\",\n" +
                "                \"vehicleNumber\": 65465465,\n" +
                "                \"id\": 2,\n" +
                "                \"timeFrame\": 8\n" +
                "            },\n" +
                "            {\n" +
                "                \"date\": \"2020-11-26 11:54:01\",\n" +
                "                \"vehicleNumber\": 465465465,\n" +
                "                \"id\": 3,\n" +
                "                \"timeFrame\": 12\n" +
                "            },\n" +
                "            {\n" +
                "                \"date\": \"2020-11-26 11:54:11\",\n" +
                "                \"vehicleNumber\": 35165465,\n" +
                "                \"id\": 4,\n" +
                "                \"timeFrame\": 16\n" +
                "            },\n" +
                "            {\n" +
                "                \"date\": \"2020-11-26 11:54:32\",\n" +
                "                \"vehicleNumber\": 4685465,\n" +
                "                \"id\": 5,\n" +
                "                \"timeFrame\": 20\n" +
                "            },\n" +
                "            {\n" +
                "                \"date\": \"2020-11-26 11:54:49\",\n" +
                "                \"vehicleNumber\": 98798798,\n" +
                "                \"id\": 6,\n" +
                "                \"timeFrame\": 24\n" +
                "            },\n" +
                "            {\n" +
                "                \"date\": \"2020-11-25 11:55:02\",\n" +
                "                \"vehicleNumber\": 4654654,\n" +
                "                \"id\": 7,\n" +
                "                \"timeFrame\": 4\n" +
                "            },\n" +
                "            {\n" +
                "                \"date\": \"2020-11-25 11:55:15\",\n" +
                "                \"vehicleNumber\": 65465465,\n" +
                "                \"id\": 8,\n" +
                "                \"timeFrame\": 8\n" +
                "            },\n" +
                "            {\n" +
                "                \"date\": \"2020-11-25 11:55:27\",\n" +
                "                \"vehicleNumber\": 465456,\n" +
                "                \"id\": 9,\n" +
                "                \"timeFrame\": 12\n" +
                "            },\n" +
                "            {\n" +
                "                \"date\": \"2020-11-25 11:55:37\",\n" +
                "                \"vehicleNumber\": 6846854,\n" +
                "                \"id\": 10,\n" +
                "                \"timeFrame\": 16\n" +
                "            },\n" +
                "            {\n" +
                "                \"date\": \"2020-11-25 11:55:48\",\n" +
                "                \"vehicleNumber\": 564654,\n" +
                "                \"id\": 11,\n" +
                "                \"timeFrame\": 20\n" +
                "            }\n" +
                "        ],\n" +
                "        \"streetList\": [\n" +
                "            {\n" +
                "                \"streetName\": \"上地街十字\",\n" +
                "                \"passedNumber\": 456456\n" +
                "            }\n" +
                "        ],\n" +
                "        \"vehicleDistributionList\": [\n" +
                "            {\n" +
                "                \"vehicleDistributionPercntage\": \"0.7\",\n" +
                "                \"id\": 1,\n" +
                "                \"vehicleDistributionName\": \"本市车辆\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleDistributionPercntage\": \"0.3\",\n" +
                "                \"id\": 2,\n" +
                "                \"vehicleDistributionName\": \"外埠\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"vehicleTypeDTOList\": [\n" +
                "            {\n" +
                "                \"vehicleTypePercentage\": \"0.41\",\n" +
                "                \"id\": 1,\n" +
                "                \"vehicleTypeName\": \"小汽车\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleTypePercentage\": \"0.23\",\n" +
                "                \"id\": 2,\n" +
                "                \"vehicleTypeName\": \"SUV\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleTypePercentage\": \"0.11\",\n" +
                "                \"id\": 3,\n" +
                "                \"vehicleTypeName\": \"MPV\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleTypePercentage\": \"0.06\",\n" +
                "                \"id\": 4,\n" +
                "                \"vehicleTypeName\": \"面包车\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleTypePercentage\": \"0.09\",\n" +
                "                \"id\": 5,\n" +
                "                \"vehicleTypeName\": \"罐式货车\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"vehicleTypePercentage\": \"0.1\",\n" +
                "                \"id\": 6,\n" +
                "                \"vehicleTypeName\": \"普通货车\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }" ;
        Object j = JSON.parse(s);
        return ResultDTO.success(j);
    }
}
