package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuanjingkj.stpbe.data.dto.Code;
import com.zhuanjingkj.stpbe.data.entity.CameraType;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.SiteDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.SiteInfoDTO;
import com.zhuanjingkj.stpbe.tmdp.exception.ServiceException;
import com.zhuanjingkj.stpbe.tmdp.mapper.DeviceMapper;
import com.zhuanjingkj.stpbe.tmdp.rto.CameraRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.DeviceRTO;
import com.zhuanjingkj.stpbe.tmdp.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<CameraType> getDeviceType() {
        return deviceMapper.getDeviceType();
    }

    @Override
    public void deleteDevice(Integer cameraId) {
        deviceMapper.deleteDevice(cameraId);
    }

    @Override
    public void insertDevice(CameraRTO cameraRTO) {
        SiteInfoDTO siteInfoDTO = deviceMapper.getSite(cameraRTO.getSiteId());
        cameraRTO.setRegionId(siteInfoDTO.getRegionId());
        deviceMapper.insertDevice(cameraRTO);
    }

    @Override
    public void updateDevice(CameraRTO cameraRTO) {
        Integer cameraId = deviceMapper.getDeviceById(cameraRTO.getCameraId());
        if(cameraId == null){
            throw  new ServiceException(Code.DEVICE_NOT_EXIST,"设备不存在");
        }
        deviceMapper.updateDevice(cameraRTO);
    }
}
