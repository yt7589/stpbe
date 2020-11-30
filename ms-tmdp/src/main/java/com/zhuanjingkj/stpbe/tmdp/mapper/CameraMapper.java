package com.zhuanjingkj.stpbe.tmdp.mapper;

import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.ImageDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.SiteInfoDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.25
 **/

@Repository
public interface CameraMapper {

    /**
     * 查询点位信息
     * @return
     */
    List<SiteInfoDTO> getSite();

    /**
     * 获取所有的摄像头
     * @return
     */
    Integer getAllCamera(@Param("siteId") String siteId);

    /**
     * 获取所有的抓拍机
     * @return
     */
    Integer getAllSnapMachine(@Param("siteId") String siteId);

    /**
     * 获取异常抓拍机
     * @return
     */
    Integer getBrokenSnapMachine();

    /**
     * 获取异常摄像头
     * @return
     */
    Integer getBrokenCamera();

    /**
     * 获取点位的设备列表
     * @param siteId
     * @return
     */
    List<CameraDTO> getCameraInfoBySite(@Param("siteId") String siteId);

    /**
     * 查询设备下的图片
     * @param tName
     * @param cameraId
     * @return
     */
    ImageDTO getImgByCameraId(@Param("tName") String tName, @Param("cameraId") String cameraId);

}
