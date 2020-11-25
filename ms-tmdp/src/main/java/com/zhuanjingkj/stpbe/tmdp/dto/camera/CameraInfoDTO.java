package com.zhuanjingkj.stpbe.tmdp.dto.camera;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on 2020.11.25
 **/
public class CameraInfoDTO extends BaseDTO {

    private Integer allCamera;

    private Integer allSnapMachine;

    private Integer brokenCamera;

    private Integer brokenSnapMachine;

    public Integer getAllCamera() {
        return allCamera;
    }

    public void setAllCamera(Integer allCamera) {
        this.allCamera = allCamera;
    }

    public Integer getAllSnapMachine() {
        return allSnapMachine;
    }

    public void setAllSnapMachine(Integer allSnapMachine) {
        this.allSnapMachine = allSnapMachine;
    }

    public Integer getBrokenCamera() {
        return brokenCamera;
    }

    public void setBrokenCamera(Integer brokenCamera) {
        this.brokenCamera = brokenCamera;
    }

    public Integer getBrokenSnapMachine() {
        return brokenSnapMachine;
    }

    public void setBrokenSnapMachine(Integer brokenSnapMachine) {
        this.brokenSnapMachine = brokenSnapMachine;
    }
}
