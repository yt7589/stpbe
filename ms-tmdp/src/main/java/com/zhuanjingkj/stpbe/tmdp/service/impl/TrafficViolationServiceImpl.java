package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuanjingkj.stpbe.data.dto.Code;
import com.zhuanjingkj.stpbe.data.entity.TrafficViolationType;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationStatisticDTO;
import com.zhuanjingkj.stpbe.tmdp.exception.ServiceException;
import com.zhuanjingkj.stpbe.tmdp.mapper.TrafficViolationMapper;
import com.zhuanjingkj.stpbe.tmdp.rto.TrafficViolationRTO;
import com.zhuanjingkj.stpbe.tmdp.service.TrafficViolationService;
import com.zhuanjingkj.stpbe.tmdp.util.CommentUtils;
import org.apache.commons.lang.StringUtils;
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

    @Override
    public PageInfo getTrafficViolation(TrafficViolationRTO trafficViolationRTO) {

        if(trafficViolationRTO == null){
            throw new ServiceException(Code.PARAMETER_ERROR,"查询参数不能为空");
        }
        if(StringUtils.isNotEmpty(trafficViolationRTO.getViolationJoinTypeName())){
            String[] violationType = trafficViolationRTO.getViolationJoinTypeName().split("-");
            trafficViolationRTO.setVehicleTypeName(violationType[0]);
            if(violationType.length == 2){
                trafficViolationRTO.setVehicleSubTypeName(violationType[1]);
            }
        }
        PageHelper.startPage(trafficViolationRTO.getPageNum(), trafficViolationRTO.getPageSize()); // 设定当前页码，以及当前页显示的条数
        //PageHelper.offsetPage(pageNum, pageSize);也可以使用此方式进行设置
        List<TrafficViolationDTO> list = trafficViolationMapper.getTrafficViolation(trafficViolationRTO);
        PageInfo<TrafficViolationDTO> pageInfo = new PageInfo<TrafficViolationDTO>(list);
        return pageInfo;
    }

    @Override
    public List<TrafficViolationType> getTrafficViolationType() {
        return trafficViolationMapper.getTrafficViolationType();
    }
}
