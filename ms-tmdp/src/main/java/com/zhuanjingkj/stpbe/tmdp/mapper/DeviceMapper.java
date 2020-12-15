package com.zhuanjingkj.stpbe.tmdp.mapper;

import com.zhuanjingkj.stpbe.data.entity.CameraType;
import com.zhuanjingkj.stpbe.tmdp.dto.RegionBaseDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.SiteInfoDTO;
import com.zhuanjingkj.stpbe.tmdp.rto.CameraRTO;
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

    /**
     * 查询设备异常
     * @return
     */
    List<CameraType> getDeviceType();

    /**
     * 删除设备
     * @param cameraId
     */
    void deleteDevice(@Param("cameraId")Integer cameraId);

    /**
     * 插入设备
     * @param cameraRTO
     */
    void insertDevice(CameraRTO cameraRTO);

    /**
     * 查询点位信息
     * @param siteId
     * @return
     */
    SiteInfoDTO getSite(@Param("siteId")Integer siteId);

    /**
     * 更新设备信息
     * @param cameraRTO
     */
    void updateDevice(CameraRTO cameraRTO);

    /**
     * 查询设备是否存在
     * @param cameraId
     * @return
     */
    Integer getDeviceById(@Param("cameraId")Integer cameraId);

    /**
     * 查询地区
     * @return
     */
    List<RegionBaseDTO> getRegion();

    /**
     * 删除地区
     * @param id
     */
    void deleteRegion(@Param("id")Integer id);

    /**
     * 根据父ID查询地区
     * @return
     */
    List<RegionBaseDTO> getRegionByParentId(RegionBaseDTO regionBaseDTO);
}
