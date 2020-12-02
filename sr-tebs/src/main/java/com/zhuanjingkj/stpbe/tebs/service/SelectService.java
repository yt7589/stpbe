package com.zhuanjingkj.stpbe.tebs.service;

import com.zhuanjingkj.stpbe.tebs.dto.CameraDTO;

/**
 * author by guoqiang
 * date on 2020.12.02
 **/
public interface SelectService {

    /**
     * 获取摄像头信息
     * @param cameraId
     * @return
     */
    CameraDTO getCamera( String cameraId);
}
