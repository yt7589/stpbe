package com.zhuanjingkj.stpbe.tmdp.mapper;

import com.zhuanjingkj.stpbe.tmdp.dto.EmphasisVehicleImgUrl;
import com.zhuanjingkj.stpbe.tmdp.dto.EmphasisVehicleInformationDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.TrafficViolationDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.23
 **/

@Repository
public interface EmphasisVehicleMapper {

    /**
     * 获取某日重点车辆的分布数量
     * @param minDate
     * @param maxDate
     * @return
     */
    List<EmphasisVehicleInformationDTO> getEmphasisVehiclePercentage(@Param("minDate") String minDate, @Param("maxDate") String maxDate);

    /**
     * 查询某日地区重点车辆信息
     * @param minDate
     * @param maxDate
     * @return
     */
    List<EmphasisVehicleInformationDTO> getEmphasisVehicleByRegion(@Param("minDate") String minDate, @Param("maxDate") String maxDate);


    /**
     * 查询某日地区重点车辆信息
     * @param minDate
     * @param maxDate
     * @return
     */
    List<EmphasisVehicleInformationDTO> getEmphasisVehicleBySite(@Param("minDate") String minDate, @Param("maxDate") String maxDate);


    /**
     * 查询重点车辆实时违章信息
     * @return
     */
    List<TrafficViolationDTO> getEmphasisVehicleViolation();

    /**
     * 获取某个时间段重点车辆的数量
     * @return
     */
    Integer getEmphasisVehicleByTimeFrame(@Param("minDate") String minDate, @Param("maxDate") String maxDate);

    /**
     * 获取当日重点车辆数量
     * @param minDate
     * @param maxDate
     * @return
     */
    Integer getTodayEmphasisVehicleNum(@Param("minDate") String minDate, @Param("maxDate") String maxDate);

    /**
     * 获取当日报警熟
     * @param minDate
     * @param maxDate
     * @return
     */
    Integer getTodayViolationNum(@Param("minDate") String minDate, @Param("maxDate") String maxDate);

    /**
     * 获取当日重点区域重点车辆数量
     * @param minDate
     * @param maxDate
     * @return
     */
    Integer getTodayEmVehicleAndEmRegionNum(@Param("minDate") String minDate, @Param("maxDate") String maxDate);

    /**
     * 查询当日在线设备总数
     * @return
     */
    Integer getOnlineDeviceNum();

    /**
     * 获取重点车辆实时图片
     * @return
     */
    List<EmphasisVehicleImgUrl> getEmphasisVehicleImg();
}
