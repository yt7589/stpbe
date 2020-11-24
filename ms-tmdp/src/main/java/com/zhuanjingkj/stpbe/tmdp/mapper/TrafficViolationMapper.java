package com.zhuanjingkj.stpbe.tmdp.mapper;

import com.zhuanjingkj.stpbe.tmdp.dto.TrafficViolationDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.TrafficViolationStatisticDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.21
 **/

@Repository
public interface TrafficViolationMapper {

    /**
     * 查询某个时间段内的违章数量
     * @return
     */
    Integer getTrafficViolationTimeFrameNumber(@Param("minDate") String minDate, @Param("maxDate") String maxDate);

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