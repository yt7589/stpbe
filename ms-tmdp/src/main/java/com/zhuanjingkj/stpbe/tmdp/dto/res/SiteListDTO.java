package com.zhuanjingkj.stpbe.tmdp.dto.res;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraInfoDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.SiteInfoDTO;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.25
 **/
public class SiteListDTO extends BaseDTO {

    private List<SiteInfoDTO> siteInfoList;

    private CameraInfoDTO cameraInfo;

    public CameraInfoDTO getCameraInfo() {
        return cameraInfo;
    }

    public void setCameraInfo(CameraInfoDTO cameraInfo) {
        this.cameraInfo = cameraInfo;
    }

    public List<SiteInfoDTO> getSiteInfoList() {
        return siteInfoList;
    }

    public void setSiteInfoList(List<SiteInfoDTO> siteInfoList) {
        this.siteInfoList = siteInfoList;
    }
}
