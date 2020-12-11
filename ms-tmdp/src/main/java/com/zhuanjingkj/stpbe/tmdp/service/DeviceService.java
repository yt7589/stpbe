package com.zhuanjingkj.stpbe.tmdp.service;

import com.github.pagehelper.PageInfo;
import com.zhuanjingkj.stpbe.data.entity.CameraType;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.DeviceRTO;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.12.10
 **/
public interface DeviceService {

    /**
     * 分页查询设备信息
     * @return
     */
    PageInfo<CameraDTO> getDevice(DeviceRTO deviceRTO);


    /**
     * 查询设备异常
     * @return
     */
    List<CameraType> getDeviceType();
}
