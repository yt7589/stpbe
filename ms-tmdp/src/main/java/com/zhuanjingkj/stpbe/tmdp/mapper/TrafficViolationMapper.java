package com.zhuanjingkj.stpbe.tmdp.mapper;

import com.zhuanjingkj.stpbe.data.entity.TrafficViolation;
import com.zhuanjingkj.stpbe.data.entity.TrafficViolationType;
import com.zhuanjingkj.stpbe.data.entity.VehicleJoinType;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationStatisticDTO;
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

    /**
     * 查询违章列表
     * @param trafficViolation
     * @return
     */
    List<TrafficViolationDTO> getTrafficViolation(TrafficViolation trafficViolation);

    /**
     * 查询违章类型
     * @return
     */
    List<TrafficViolationType>  getTrafficViolationType();
}
