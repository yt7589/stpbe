package com.zhuanjingkj.stpbe.tmdp.mapper;

import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.12.10
 **/

@Repository
public interface DeviceMapper {

    /**
     * 查询设备列表
     * @return
     */
    List<CameraDTO>  getDevice(@Param("cameraCode") String cameraCode, @Param("cameraTypeId")Integer cameraTypeId);
}
