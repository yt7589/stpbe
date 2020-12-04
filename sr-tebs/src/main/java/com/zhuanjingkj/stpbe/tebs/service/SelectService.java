package com.zhuanjingkj.stpbe.tebs.service;

import com.zhuanjingkj.stpbe.data.entity.VehicleDistribution;
import com.zhuanjingkj.stpbe.tebs.dto.CameraDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.12.02
 **/
public interface SelectService {

    /**
     * 获取摄像头信息
     * @param cameraId
     * @return
     */
    CameraDTO getCamera( String cameraId);


    /**
     * 根据uuid查询ID
     * @param tableName
     * @param uid
     * @return
     */
    Long getImage(String tableName, String uid);

    /**
     * 查询车辆分布情况
     * @return
     */
    List<VehicleDistribution> getVehicleDistribution();
}
