package com.zhuanjingkj.stpbe.tmdp.service;

import com.github.pagehelper.PageInfo;
import com.zhuanjingkj.stpbe.data.entity.CameraType;
import com.zhuanjingkj.stpbe.tmdp.dto.RegionBaseDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.CameraRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.DeviceRTO;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 删除设备
     * @param cameraId
     */
    void deleteDevice(Integer cameraId);

    /**
     * 插入设备
     * @param cameraRTO
     */
    void insertDevice(CameraRTO cameraRTO);

    /**
     * 更新设备信息
     * @param cameraRTO
     */
    void updateDevice(CameraRTO cameraRTO);

    /**
     * 查询地区
     * @return
     */
    PageInfo<RegionBaseDTO> getRegion(Integer pageNum,Integer pageSize);


    /**
     * 删除地区
     * @param id
     */
    void deleteRegion(Integer id);
}
