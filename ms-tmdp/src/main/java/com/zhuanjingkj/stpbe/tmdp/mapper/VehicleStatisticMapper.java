package com.zhuanjingkj.stpbe.tmdp.mapper;

import com.zhuanjingkj.stpbe.data.entity.VehicleJoinType;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleStatisticMapper {
    /**
     * 查询车辆的本市的分布信息
     * @return
     */
    List<VehicleDistributionDTO> getVehicleDistribution();

    /**
     * 查询车辆类型的分布信息
     * @return
     */
    List<VehicleTypeDTO> getVehicleType();

    /**
     * 查询分时段过车信息
     * @param minDate
     * @param maxDate
     * @return
     */
    List<TimeFrameNumberDTO> getTimeFrameNumber(@Param("minDate") String minDate, @Param("maxDate") String maxDate);

    /**
     * 查询车辆类型流量
     * @return
     */
    List<VehicleTypeNumberDTO> getVehicleTypeNumber();

    /**
     * 查询拥堵路段的车流量
     * @param minDate
     * @param maxDate
     * @return
     */
    List<VehicleStatisticDTO> getVehicleStatisticByStreet(@Param("minDate") String minDate, @Param("maxDate") String maxDate);

    /**
     * 查询区域车流量
     * @param minDate
     * @param maxDate
     * @return
     */
    List<VehicleStatisticDTO> getVehicleStatisticByRegion(@Param("minDate") String minDate, @Param("maxDate") String maxDate);

    /**
     * 查询某个时间段的过车量
     * @param minDate
     * @param maxDate
     * @return
     */
    Integer getPassedNumber(@Param("minDate") String minDate, @Param("maxDate") String maxDate);

    /**
     * 获取区域名称
     * @return
     */
    List<VehicleStatisticDTO> getRegion(@Param("level") String level);

    /**
     * 更新今天日期
     * @param date
     */
    void updateTodayTimeFrame(@Param("date") String date);

    /**
     * 更新昨天日期
     * @param date
     */
    void updateYesterdayTimeFrame(@Param("date") String date);


    void updateRegion(@Param("date") String date);

    void updatePassedNumber(@Param("date") String date);

    /**
     * 获取拼接车辆类型列表
     * @return
     */
    List<VehicleJoinType> getVehicleJoinType();
}
