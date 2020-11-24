package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.*;
import com.zhuanjingkj.stpbe.tmdp.mapper.EmphasisVehicleMapper;
import com.zhuanjingkj.stpbe.tmdp.service.EmphasisVehicleService;
import com.zhuanjingkj.stpbe.tmdp.util.CommentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * author by guoqiang
 * date on 2020.11.23
 **/

@Service
public class EmphasisVehicleServiceImpl implements EmphasisVehicleService {

    @Autowired
    private EmphasisVehicleMapper emphasisVehicleMapper;

    @Override
    public List<EmphasisVehicleInformationDTO> getEmphasisVehiclePercentage() {
        Map<String,String> map = this.getDateStr();
        List<EmphasisVehicleInformationDTO> list = emphasisVehicleMapper.getEmphasisVehiclePercentage(map.get("minDate"),map.get("maxDate"));
        if(list==null){
            return null;
        }
        AtomicReference<Integer> num = new AtomicReference<>(0);
        list.forEach(emphasisVehicleInformationDTO -> {
            num.updateAndGet(v -> v + emphasisVehicleInformationDTO.getEmphasisVehicleNum());
        });
        BigDecimal total = new BigDecimal(num.get());
        list.forEach(emphasisVehicleInformationDTO ->{
            BigDecimal emphasisVehicleNum = new BigDecimal(emphasisVehicleInformationDTO.getEmphasisVehicleNum());
            String percentage = emphasisVehicleNum.divide(total).toString();
            emphasisVehicleInformationDTO.setPercentage(percentage);
        });
        return list;
    }

    @Override
    public List<EmphasisVehicleInformationDTO> getEmphasisVehicleByRegion() {
        Map<String,String> map = this.getDateStr();
        return emphasisVehicleMapper.getEmphasisVehicleByRegion(map.get("minDate"),map.get("maxDate"));
    }

    @Override
    public List<EmphasisVehicleInformationDTO> getEmphasisVehicleBySite() {
        Map<String,String> map = this.getDateStr();
        return emphasisVehicleMapper.getEmphasisVehicleBySite(map.get("minDate"),map.get("maxDate"));
    }

    @Override
    public List<TrafficViolationDTO> getEmphasisVehicleViolation() {
        return emphasisVehicleMapper.getEmphasisVehicleViolation();
    }

    @Override
    public List<EmphasisVehicleTimeFrameDTO> getEmphasisVehicleByTimeFrame() {

        Date today = new Date();
        Date yesterday = new Date(today.getTime()-86400000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = sdf.format(today);
        String yesterdayStr = sdf.format(yesterday);

        Map<Integer,String> map = CommentUtils.generateDayTime(null);
        List<EmphasisVehicleTimeFrameDTO> list = new ArrayList<>();
        for(int i=0;i<12;i++){
            EmphasisVehicleTimeFrameDTO emphasisVehicleTimeFrameDTO = new EmphasisVehicleTimeFrameDTO();
            Integer trafficViolationNum = emphasisVehicleMapper.getEmphasisVehicleByTimeFrame(map.get(i),map.get(i+1));
            emphasisVehicleTimeFrameDTO.setTimeFrame(i*2+2);
            emphasisVehicleTimeFrameDTO.setEmphasisVehicleNum(trafficViolationNum);
            emphasisVehicleTimeFrameDTO.setDate(todayStr);
            list.add(emphasisVehicleTimeFrameDTO);
        }
        Map<Integer,String> mapYesterday = CommentUtils.generateDayTime(yesterday);
        for(int i=0;i<12;i++){
            EmphasisVehicleTimeFrameDTO emphasisVehicleTimeFrameDTO = new EmphasisVehicleTimeFrameDTO();
            Integer trafficViolationNum = emphasisVehicleMapper.getEmphasisVehicleByTimeFrame(map.get(i),map.get(i+1));
            emphasisVehicleTimeFrameDTO.setTimeFrame(i*2+2);
            emphasisVehicleTimeFrameDTO.setEmphasisVehicleNum(trafficViolationNum);
            emphasisVehicleTimeFrameDTO.setDate(yesterdayStr);
            list.add(emphasisVehicleTimeFrameDTO);
        }
        return list;
    }

    @Override
    public EmphasisVehicleNumberDTO getEmphasisVehicleNumber() {
        Map<String,String> map = this.getDateStr();
        Integer currentEmphasisVehicleNum = emphasisVehicleMapper.getTodayEmphasisVehicleNum(map.get("minDate"),map.get("maxDate"));
        Integer alarmNum = emphasisVehicleMapper.getTodayViolationNum(map.get("minDate"),map.get("maxDate"));
        Integer emphasisVehicleEmphasisRegionNum = emphasisVehicleMapper.getTodayEmVehicleAndEmRegionNum(map.get("minDate"),map.get("maxDate"));
        Integer onlineDevice = emphasisVehicleMapper.getOnlineDeviceNum();
        EmphasisVehicleNumberDTO emphasisVehicleNumberDTO = new EmphasisVehicleNumberDTO();
        emphasisVehicleNumberDTO.setCurrentEmphasisVehicleNum(currentEmphasisVehicleNum);
        emphasisVehicleNumberDTO.setAlarmNum(alarmNum);
        emphasisVehicleNumberDTO.setEmphasisVehicleEmphasisRegionNum(emphasisVehicleEmphasisRegionNum);
        emphasisVehicleNumberDTO.setOnlineDevice(onlineDevice);
        return emphasisVehicleNumberDTO;
    }

    private Map<String,String> getDateStr(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,String> map = new HashMap<>();
        String minDate = sdf.format(date) + " 00:00:00";
        String maxDate = sdf.format(date) + " 23:59:59";
        map.put("minDate",minDate);
        map.put("maxDate",maxDate);
        return map;
    }
}
