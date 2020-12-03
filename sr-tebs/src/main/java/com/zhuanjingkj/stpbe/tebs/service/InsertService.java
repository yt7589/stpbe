package com.zhuanjingkj.stpbe.tebs.service;

import com.zhuanjingkj.stpbe.tebs.rto.ImageRTO;
import com.zhuanjingkj.stpbe.tebs.rto.VehicleInformationRTO;

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
}
