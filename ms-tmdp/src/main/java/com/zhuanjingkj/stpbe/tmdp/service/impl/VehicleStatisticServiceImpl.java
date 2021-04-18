package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.data.dto.Code;
import com.zhuanjingkj.stpbe.data.entity.VehicleJoinType;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.*;
import com.zhuanjingkj.stpbe.tmdp.exception.ServiceException;
import com.zhuanjingkj.stpbe.tmdp.mapper.VehicleStatisticMapper;
import com.zhuanjingkj.stpbe.tmdp.service.VehicleStatisticService;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
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


        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = sdf1.format(date);
        String yesterdayStr1 = sdf1.format(yesterday);
        vehicleStatisticMapper.updateTodayTimeFrame(dateStr1);
        vehicleStatisticMapper.updateYesterdayTimeFrame(yesterdayStr1);
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

        Date date1 = new Date(date.getTime() - 1800000L);
        String dateStr1 = sdf.format(date1) ;
        vehicleStatisticMapper.updateRegion(dateStr1);
        return vehicleStatisticMapper.getVehicleStatisticByStreet(dateBeforeStr,dateStr);
    }

    @Override
    public List<VehicleStatisticDTO> getVehicleStatisticByRegion() throws ServiceException{

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date) ;
        Date dateBefore = new Date(date.getTime() - 3600000L);
        String dateBeforeStr = sdf.format(dateBefore) ;
        List<VehicleStatisticDTO> regionList = vehicleStatisticMapper.getRegion("2");
        if(regionList==null|| regionList.size()==0){
            throw new ServiceException(Code.NO_REGION,"没有可显示的区域");
        }
        List<VehicleStatisticDTO> passNumList = vehicleStatisticMapper.getVehicleStatisticByRegion(dateBeforeStr,dateStr);
        if(passNumList==null || passNumList.size()==0){
           return regionList;
        }
        regionList.forEach(v->{
            passNumList.forEach(v1->{
                if(StringUtils.equals(v.getRegionName(),v1.getRegionName())){
                    v.setPassedNumber(v1.getPassedNumber());
                }
            });
        });
        return regionList;
    }

    @Override
    public VehiclePassedNumberDTO getVehiclePassedNumber() {
        VehiclePassedNumberDTO vehiclePassedNumberDTO = new VehiclePassedNumberDTO();
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        String todayMin = sdf.format(today) + MIN_TIME;
        String todayMax = sdf.format(today) + MAX_TIME;

        vehicleStatisticMapper.updatePassedNumber(todayMin);
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

    @Override
    public List<VehicleJoinType> getVehicleJoinType() {
        return vehicleStatisticMapper.getVehicleJoinType();
    }
}
