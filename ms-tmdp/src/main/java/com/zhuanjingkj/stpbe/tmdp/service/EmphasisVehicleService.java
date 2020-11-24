package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.tmdp.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.23
 **/
public interface EmphasisVehicleService {


    /**
     * 获取当日重点车辆的分布数量
     * @return
     */
    List<EmphasisVehicleInformationDTO> getEmphasisVehiclePercentage();

    /**
     * 查询某日地区重点车辆信息
     * @return
     */
    List<EmphasisVehicleInformationDTO> getEmphasisVehicleByRegion();


    /**
     * 查询某日点位重点车辆信息
     * @return
     */
    List<EmphasisVehicleInformationDTO> getEmphasisVehicleBySite();

    /**
     * 查询重点车辆实时违章信息
     * @return
     */
    List<TrafficViolationDTO> getEmphasisVehicleViolation();


    /**
     * 获取昨天和今天偶数时间段的重点车数量
     * @return
     */
    List<EmphasisVehicleTimeFrameDTO> getEmphasisVehicleByTimeFrame();

    /**
     * 获取重点车辆的基本数据信息
     * @return
     */
    EmphasisVehicleNumberDTO getEmphasisVehicleNumber();

    /**
     * 获取重点车辆实时图片
     * @return
     */
    List<EmphasisVehicleImgUrl> getEmphasisVehicleImg();
}
