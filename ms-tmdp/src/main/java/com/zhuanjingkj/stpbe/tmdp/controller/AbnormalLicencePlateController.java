package com.zhuanjingkj.stpbe.tmdp.controller;

import com.alibaba.fastjson.JSON;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.licencePlate.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * author by guoqiang
 * date on 2020.12.08
 **/
@RestController
@RequestMapping("/abnormal/licence/plate")
@CrossOrigin(origins = "*")
public class AbnormalLicencePlateController {

    @GetMapping("/statistic")
    public ResultDTO<AbnormalLicencePlateListDTO> getStatistic(){

        List<TimeFrameAbnormalLicencePlateDTO> timeFrameAbnormalLicencePlateList = new ArrayList<>();
        Random r = new Random(1);
        for(int i=0;i<24;i++){
            TimeFrameAbnormalLicencePlateDTO abnormalLicencePlate = new TimeFrameAbnormalLicencePlateDTO();
            String timeFrame = String.valueOf(i);
            if(timeFrame.length()==1){
                timeFrame = "0"+timeFrame+":00";
            }else{
                timeFrame = timeFrame+":00";
            }
            abnormalLicencePlate.setTimeFrame(timeFrame);
            abnormalLicencePlate.setAbnormalLicencePlateNum(r.nextInt(100));
            timeFrameAbnormalLicencePlateList.add(abnormalLicencePlate);
        }

        List<RegionAbnormalLicencePlateDTO> regionAbnormalLicencePlateList = new ArrayList<>();
        String[] regions = {"海淀区","东城区","西城区","朝阳区","丰台区","房山区","石景山区","通州区","怀柔区","密云区"};
        for(int i=0;i<10;i++){
            RegionAbnormalLicencePlateDTO regionAbnormalLicencePlate = new RegionAbnormalLicencePlateDTO();
            regionAbnormalLicencePlate.setRegionName(regions[i]);
            regionAbnormalLicencePlate.setRegionAbnormalLicencePlateNum(r.nextInt(300));
            regionAbnormalLicencePlateList.add(regionAbnormalLicencePlate);
        }

        String[] statisticName = {"今日无牌过车数量","今日套牌过车数量","今日假牌车过车数量","今日号牌遮挡过车数量"};
        String[] abnormalLicencePlateTypes = {"无牌车","套牌车","假车牌","号牌遮"};

        List<AbnormalLicencePlateStatisticDTO> abnormalLicencePlateStatisticList = new ArrayList<>();
        List<AbnormalLicencePlateVehicleDTO> abnormalLicencePlateVehicleList = new ArrayList<>();
        for(int i=0;i<4;i++){
            AbnormalLicencePlateStatisticDTO abnormalLicencePlateStatistic = new AbnormalLicencePlateStatisticDTO();
            abnormalLicencePlateStatistic.setName(statisticName[i]);
            abnormalLicencePlateStatistic.setNum(r.nextInt(1000));
            abnormalLicencePlateStatisticList.add(abnormalLicencePlateStatistic);

            AbnormalLicencePlateVehicleDTO abnormalLicencePlateVehicle = new AbnormalLicencePlateVehicleDTO();
            abnormalLicencePlateVehicle.setAbnormalLicencePlateType(abnormalLicencePlateTypes[i]);
            int a = i+1;
            abnormalLicencePlateVehicle.setLocation("上地"+a+"街");
            abnormalLicencePlateVehicle.setDate("2020-12-09 12:00:00");
            abnormalLicencePlateVehicle.setImgUrl("http://222.128.117.234:8090/cloud/vehicle_images/v001.jpg");
            abnormalLicencePlateVehicleList.add(abnormalLicencePlateVehicle);

        }

        AbnormalLicencePlateListDTO abnormalLicencePlateListDTO = new AbnormalLicencePlateListDTO();

        abnormalLicencePlateListDTO.setAbnormalLicencePlateStatisticList(abnormalLicencePlateStatisticList);
        abnormalLicencePlateListDTO.setAbnormalLicencePlateVehicleList(abnormalLicencePlateVehicleList);
        abnormalLicencePlateListDTO.setRegionAbnormalLicencePlateList(regionAbnormalLicencePlateList);
        abnormalLicencePlateListDTO.setTimeFrameAbnormalLicencePlateList(timeFrameAbnormalLicencePlateList);
        return ResultDTO.success(abnormalLicencePlateListDTO);
    }
}
