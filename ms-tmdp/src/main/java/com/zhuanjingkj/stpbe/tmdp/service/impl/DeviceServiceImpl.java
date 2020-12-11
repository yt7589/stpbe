package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.SiteDTO;
import com.zhuanjingkj.stpbe.tmdp.mapper.DeviceMapper;
import com.zhuanjingkj.stpbe.tmdp.rto.DeviceRTO;
import com.zhuanjingkj.stpbe.tmdp.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author by guoqiang
 * date on 2020.12.10
 **/

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceMapper deviceMapper;
    @Override
    public PageInfo<CameraDTO> getDevice(DeviceRTO deviceRTO) {
        PageHelper.startPage(deviceRTO.getPageNum(), deviceRTO.getPageSize());
        PageInfo<CameraDTO> pageInfo = new PageInfo<CameraDTO>(deviceMapper.getDevice(deviceRTO.getCameraCode(),deviceRTO.getCameraTypeId()));
        return pageInfo;
    }
}
