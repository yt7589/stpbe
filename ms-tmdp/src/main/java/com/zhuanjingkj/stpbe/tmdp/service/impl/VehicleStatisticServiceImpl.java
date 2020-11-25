package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.*;
import com.zhuanjingkj.stpbe.tmdp.mapper.VehicleStatisticMapper;
import com.zhuanjingkj.stpbe.tmdp.service.VehicleStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * author by guoqiang
 * date on 2020.11.20
 **/

@Service
public class VehicleStatisticServiceImpl implements VehicleStatisticService {

    private static final String MIN_TIME= " 00:00:00";
    private static final String MAX_TIME = " 23:59:59";

    @Autowired
    private VehicleStatisticMapper vehicleStatisticMapper;

    @Override
    public List<VehicleDistributionDTO> getVehicleDistribution() {
        return vehicleStatisticMapper.getVehicleDistribution();
    }

    @Override
    public List<VehicleTypeDTO> getVehicleType() {
        return vehicleStatisticMapper.getVehicleType();
    }

    @Override
    public List<TimeFrameNumberDTO> getTimeFrameNumber() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date) + MAX_TIME;
        Date yesterday = new Date(date.getTime() - 86400000L);
        String yesterdayStr = sdf.format(yesterday) + MIN_TIME;
        return vehicleStatisticMapper.getTimeFrameNumber(yesterdayStr,dateStr);
    }

    @Override
    public List<VehicleTypeNumberDTO> getVehicleTypeNumber() {
        return vehicleStatisticMapper.getVehicleTypeNumber();
    }

    @Override
    public List<VehicleStatisticDTO> getVehicleStatisticByStreet() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date) ;
        Date dateBefore = new Date(date.getTime() - 3600000L);
        String dateBeforeStr = sdf.format(dateBefore) ;
        return vehicleStatisticMapper.getVehicleStatisticByStreet(dateBeforeStr,dateStr);
    }

    @Override
    public List<VehicleStatisticDTO> getVehicleStatisticByRegion() {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date) ;
        Date dateBefore = new Date(date.getTime() - 3600000L);
        String dateBeforeStr = sdf.format(dateBefore) ;
        return vehicleStatisticMapper.getVehicleStatisticByRegion(dateBeforeStr,dateStr);    }

    @Override
    public VehiclePassedNumberDTO getVehiclePassedNumber() {
        VehiclePassedNumberDTO vehiclePassedNumberDTO = new VehiclePassedNumberDTO();
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayMin = sdf.format(today) + MIN_TIME;
        String todayMax = sdf.format(today) + MAX_TIME;
        int todayPassedNumber = Optional.ofNullable(vehicleStatisticMapper.getPassedNumber(todayMin,todayMax)).orElse(0);

        Date sevenDayBefore = new Date(today.getTime()-51840000L);
        String sevenDayBeforeMin = sdf.format(sevenDayBefore) + MIN_TIME;
        int sevenDayPassedNumber = Optional.ofNullable(vehicleStatisticMapper.getPassedNumber(sevenDayBeforeMin,todayMax)).orElse(0);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        String currentMonthFirstDay = sdf1.format(today) + "-01" + MIN_TIME;
        int currentMonthPassedNumber = Optional.ofNullable(vehicleStatisticMapper.getPassedNumber(currentMonthFirstDay,todayMax)).orElse(0);

        String[] temp = sdf.format(today).split("-");
        String date = temp[2];
        Integer num = null;
        if(date.charAt(0)=='0'){
            num = Integer.valueOf(String.valueOf(date.charAt(1)));
        }else {
            num = Integer.valueOf(date);
        }
        int dailyMean = currentMonthPassedNumber/num;
        vehiclePassedNumberDTO.setTodayPassedNumber(todayPassedNumber);
        vehiclePassedNumberDTO.setSevenDayPassedNumber(sevenDayPassedNumber);
        vehiclePassedNumberDTO.setCurrentMonthPassedNumber(currentMonthPassedNumber);
        vehiclePassedNumberDTO.setDailyMean(dailyMean);
        return vehiclePassedNumberDTO;
    }
}
