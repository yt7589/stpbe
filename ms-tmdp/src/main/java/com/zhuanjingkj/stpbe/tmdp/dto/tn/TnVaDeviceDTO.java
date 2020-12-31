package com.zhuanjingkj.stpbe.tmdp.dto.tn;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class TnVaDeviceDTO extends BaseDTO {
    @JSONField(name = "camera")
    private Integer camera;
    @JSONField(name = "abnormal_camera")
    private Integer abnormal_camera;
    @JSONField(name = "snapshot")
    private Integer snapshot;
    @JSONField(name = "abnormal_snapshot")
    private Integer abnormal_snapshot;

    public TnVaDeviceDTO(Integer camera, Integer abnormal_camera, Integer snapshot, Integer abnormal_snapshot) {
        this.camera = camera;
        this.abnormal_camera = abnormal_camera;
        this.snapshot = snapshot;
        this.abnormal_snapshot = abnormal_snapshot;
    }

    public Integer getCamera() {
        return camera;
    }

    public void setCamera(Integer camera) {
        this.camera = camera;
    }

    public Integer getAbnormal_camera() {
        return abnormal_camera;
    }

    public void setAbnormal_camera(Integer abnormal_camera) {
        this.abnormal_camera = abnormal_camera;
    }

    public Integer getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Integer snapshot) {
        this.snapshot = snapshot;
    }

    public Integer getAbnormal_snapshot() {
        return abnormal_snapshot;
    }

    public void setAbnormal_snapshot(Integer abnormal_snapshot) {
        this.abnormal_snapshot = abnormal_snapshot;
    }
}

