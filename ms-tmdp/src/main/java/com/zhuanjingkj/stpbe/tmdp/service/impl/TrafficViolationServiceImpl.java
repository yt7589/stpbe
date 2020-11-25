package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationStatisticDTO;
import com.zhuanjingkj.stpbe.tmdp.mapper.TrafficViolationMapper;
import com.zhuanjingkj.stpbe.tmdp.service.TrafficViolationService;
import com.zhuanjingkj.stpbe.tmdp.util.CommentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        Map<Integer,String> map = CommentUtils.generateDayTime(null);
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
