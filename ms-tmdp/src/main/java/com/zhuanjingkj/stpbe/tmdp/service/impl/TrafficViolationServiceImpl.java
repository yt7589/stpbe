package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.TrafficViolationDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.TrafficViolationStatisticDTO;
import com.zhuanjingkj.stpbe.tmdp.mapper.TrafficViolationMapper;
import com.zhuanjingkj.stpbe.tmdp.service.TrafficViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * author by guoqiang
 * date on 2020.11.21
 **/

@Service
public class TrafficViolationServiceImpl implements TrafficViolationService {

    @Autowired
    TrafficViolationMapper trafficViolationMapper;


    @Override
    public List<TrafficViolationStatisticDTO> getTrafficViolationTimeFrameNumber() {

        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<Integer,String> map = new HashMap<>();
        String todayStr = sdf.format(today);
        String zero = todayStr + " 00:00:00";
        map.put(0,zero);
        String two = todayStr + " 02:00:00";
        map.put(1,two);
        String four = todayStr + " 04:00:00";
        map.put(2,four);
        String six = todayStr + " 06:00:00";
        map.put(3,six);
        String eight = todayStr + " 08:00:00";
        map.put(4,eight);
        String ten = todayStr + " 10:00:00";
        map.put(5,ten);
        String twelve = todayStr + " 12:00:00";
        map.put(6,twelve);
        String fourth = todayStr + " 14:00:00";
        map.put(7,fourth);
        String sixteen = todayStr + " 16:00:00";
        map.put(8,sixteen);
        String eighteen = todayStr + " 18:00:00";
        map.put(9,eighteen);
        String twenty = todayStr + " 20:00:00";
        map.put(10,twenty);
        String twentyTwo = todayStr + " 22:00:00";
        map.put(11,twentyTwo);
        String twentyFour = todayStr + " 23:59:59";
        map.put(12,twentyFour);
        List<TrafficViolationStatisticDTO> trafficViolationStatisticList = new ArrayList<>();
        for(int i=0;i<12;i++){
            TrafficViolationStatisticDTO trafficViolationStatisticDTO = new TrafficViolationStatisticDTO();
            Integer trafficViolationNum = trafficViolationMapper.getTrafficViolationTimeFrameNumber(map.get(i),map.get(i+1));
            trafficViolationStatisticDTO.setTimeFrame(i*2+2);
            trafficViolationStatisticDTO.setTrafficViolationNum(trafficViolationNum);
            trafficViolationStatisticList.add(trafficViolationStatisticDTO);
        }
        return trafficViolationStatisticList;
    }

    @Override
    public List<TrafficViolationStatisticDTO> getTrafficViolationTypeNumber() {
        return trafficViolationMapper.getTrafficViolationTypeNumber();
    }

    @Override
    public List<TrafficViolationDTO> getTrafficViolationByTime() {
        return trafficViolationMapper.getTrafficViolationByTime();
    }
}
