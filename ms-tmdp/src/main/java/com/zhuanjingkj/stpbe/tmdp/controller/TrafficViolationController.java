package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.res.TrafficViolationListDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationStatisticDTO;
import com.zhuanjingkj.stpbe.tmdp.service.TrafficViolationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.21
 **/

@RestController
@RequestMapping("/traffic/violation")
public class TrafficViolationController {

    @Autowired
    private TrafficViolationService trafficViolationService;

    @GetMapping()
    public ResultDTO<TrafficViolationListDTO> getTrafficViolationInfo() {
        TrafficViolationListDTO trafficViolationListDTO = new TrafficViolationListDTO();

        List<TrafficViolationStatisticDTO> trafficViolationTimeFrameNumberList = trafficViolationService.getTrafficViolationTimeFrameNumber();
        List<TrafficViolationStatisticDTO> trafficViolationTypeNumberList = trafficViolationService.getTrafficViolationTypeNumber();
        List<TrafficViolationDTO> trafficViolationList = trafficViolationService.getTrafficViolationByTime();
        trafficViolationListDTO.setTrafficViolationTimeFrameNumberList(trafficViolationTimeFrameNumberList);
        trafficViolationListDTO.setTrafficViolationTypeNumberList(trafficViolationTypeNumberList);
        trafficViolationListDTO.setTrafficViolationList(trafficViolationList);
        return ResultDTO.success(trafficViolationListDTO);
    }
}
