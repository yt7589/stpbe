package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraInfoDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.ImageDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.SiteInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.25
 **/
public interface CameraService {

    /**
     * 查询点位信息
     * @return
     */
    List<SiteInfoDTO> getSite();

    /**
     * 获取拍摄设备的基本数据信息
     * @return
     */
    CameraInfoDTO getCameraInfo();

    /**
     * 获取点位的设备列表
     * @param siteId
     * @return
     */
    List<CameraDTO> getCameraInfoBySite(String siteId);

    /**
     * 获取所有的摄像头
     * @return
     */
    Integer getAllCamera(String siteId);

    /**
     * 获取所有的抓拍机
     * @return
     */
    Integer getAllSnapMachine(String siteId);



    /**
     * 查询设备下的图片
     * @param cameraId
     * @return
     */
    ImageDTO getImgByCameraId(String cameraId);
}
