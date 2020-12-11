package com.zhuanjingkj.stpbe.tmdp.service;

import com.github.pagehelper.PageInfo;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.DeviceRTO;

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
}
