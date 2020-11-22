package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.tmdp.dto.TrafficViolationDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.TrafficViolationStatisticDTO;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.21
 **/
public interface TrafficViolationService {


    /**
     * 查询某个时间段内的违章数量
     * @return
     */
    List<TrafficViolationStatisticDTO> getTrafficViolationTimeFrameNumber();

    /**
     * 查询不同违章类型的违章数量
     * @return
     */
    List<TrafficViolationStatisticDTO> getTrafficViolationTypeNumber();

    /**
     * 查询实时违章车辆信息
     * @return
     */
    List<TrafficViolationDTO> getTrafficViolationByTime();
}
