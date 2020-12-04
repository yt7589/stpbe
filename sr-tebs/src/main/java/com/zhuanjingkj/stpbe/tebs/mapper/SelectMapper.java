package com.zhuanjingkj.stpbe.tebs.mapper;

import com.zhuanjingkj.stpbe.data.entity.VehicleDistribution;
import com.zhuanjingkj.stpbe.tebs.dto.CameraDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.12.02
 **/
@Repository
public interface SelectMapper {

    /**
     * 获取摄像头信息
     * @param cameraId
     * @return
     */
    CameraDTO getCamera(@Param("cameraId") String cameraId);

    /**
     * 根据uuid查询ID
     * @param tableName
     * @param uid
     * @return
     */
    Long getImage(@Param("tableName")String tableName,@Param("uid")String uid);

    /**
     * 查询车辆分布情况
     * @param date
     * @return
     */
    List<VehicleDistribution> getVehicleDistribution(@Param("date")String date);
}
