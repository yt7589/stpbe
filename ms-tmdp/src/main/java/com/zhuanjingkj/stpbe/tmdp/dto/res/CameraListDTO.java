package com.zhuanjingkj.stpbe.tmdp.dto.res;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.CameraDTO;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.25
 **/
public class CameraListDTO extends BaseDTO {

    private List<CameraDTO> cameraList;

    private Integer cameraNum;

    private Integer snapMachineNum;

    public List<CameraDTO> getCameraList() {
        return cameraList;
    }

    public void setCameraList(List<CameraDTO> cameraList) {
        this.cameraList = cameraList;
    }

    public Integer getCameraNum() {
        return cameraNum;
    }

    public void setCameraNum(Integer cameraNum) {
        this.cameraNum = cameraNum;
    }

    public Integer getSnapMachineNum() {
        return snapMachineNum;
    }

    public void setSnapMachineNum(Integer snapMachineNum) {
        this.snapMachineNum = snapMachineNum;
    }
}
