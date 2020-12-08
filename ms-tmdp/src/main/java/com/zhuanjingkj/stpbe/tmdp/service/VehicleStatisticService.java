package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.entity.VehicleJoinType;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.*;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.20
 **/
public interface VehicleStatisticService {

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
     * @return
     */
    List<TimeFrameNumberDTO> getTimeFrameNumber();

    /**
     * 查询车辆类型流量
     * @return
     */
    List<VehicleTypeNumberDTO> getVehicleTypeNumber();

    /**
     * 查询拥堵路段的车流量
     * @return
     */
    List<VehicleStatisticDTO> getVehicleStatisticByStreet();

    /**
     * 查询区域车流量
     * @return
     */
    List<VehicleStatisticDTO> getVehicleStatisticByRegion();

    /**
     * 查询不同时间段的过车量
     * @return
     */
    VehiclePassedNumberDTO getVehiclePassedNumber();

    /**
     * 获取拼接车辆类型列表
     * @return
     */
    List<VehicleJoinType> getVehicleJoinType();
}
