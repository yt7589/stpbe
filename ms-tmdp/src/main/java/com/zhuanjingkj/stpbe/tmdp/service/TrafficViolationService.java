package com.zhuanjingkj.stpbe.tmdp.service;

import com.github.pagehelper.PageInfo;
import com.zhuanjingkj.stpbe.data.entity.TrafficViolation;
import com.zhuanjingkj.stpbe.data.entity.TrafficViolationType;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationStatisticDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.TrafficViolationRTO;

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

    /**
     * 分页查询违章列表
     * @param trafficViolationRTO
     * @return
     */
    PageInfo getTrafficViolation(TrafficViolationRTO trafficViolationRTO);

    /**
     * 查询违章类型
     * @return
     */
    List<TrafficViolationType>  getTrafficViolationType();
}
