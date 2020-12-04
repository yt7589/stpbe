package com.zhuanjingkj.stpbe.tebs.service;

import com.zhuanjingkj.stpbe.data.entity.VehicleDistribution;
import com.zhuanjingkj.stpbe.tebs.rto.ImageRTO;
import com.zhuanjingkj.stpbe.tebs.rto.VehicleInformationRTO;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.12.02
 **/
public interface InsertService {

    /**
     * 插入图片
     */
    void insertImage(ImageRTO image);

    /**
     * 插入车辆基本信息
     * @param vehicleInformationRTO
     */
    void insertVehicleInfo(VehicleInformationRTO vehicleInformationRTO);

    /**
     * 插入当日新增车辆分布
     * @param list
     */
    void insertVehicleDistribution(List<VehicleDistribution> list);

    /**
     * 更新当日车辆分布
     * @param list
     */
    void updateVehicleDistribution(List<VehicleDistribution> list);
}
