package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuanjingkj.stpbe.data.dto.Code;
import com.zhuanjingkj.stpbe.data.entity.CameraType;
import com.zhuanjingkj.stpbe.tmdp.dto.RegionBaseDTO;
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
        PageInfo<CameraDTO> pageInfo = new PageInfo<>(deviceMapper.getDevice(deviceRTO.getCameraCode(),deviceRTO.getCameraTypeId()));
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

    @Override
    public PageInfo<RegionBaseDTO> getRegion(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        PageInfo<RegionBaseDTO> pageInfo = new PageInfo<>(deviceMapper.getRegion());
        return pageInfo;
    }

    @Override
    public void deleteRegion(Integer id) {
        RegionBaseDTO regionBaseDTO = new RegionBaseDTO();
        regionBaseDTO.setParentId(id);
        List<RegionBaseDTO> list = deviceMapper.getRegionByParentId(regionBaseDTO);
        if(list !=null && list.size()>0){
            throw new ServiceException(Code.SUB_REGION_EXIST,"此地区存在未删除的子地区");
        }
        deviceMapper.deleteRegion(id);
    }
}
