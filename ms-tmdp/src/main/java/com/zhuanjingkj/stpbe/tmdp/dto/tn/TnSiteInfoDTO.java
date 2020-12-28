package com.zhuanjingkj.stpbe.tmdp.dto.tn;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 点位信息
 */
public class TnSiteInfoDTO extends BaseDTO {
    @JSONField(name = "camera")
    private Integer camera; //摄像头数量
    @JSONField(name = "snapshot")
    private Integer snapshot; //抓拍机数量
    @JSONField(name = "recs")
    List<TnSdInfoDTO> recs = new ArrayList<>();

    public Integer getCamera() {
        return camera;
    }

    public void setCamera(Integer camera) {
        this.camera = camera;
    }

    public Integer getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Integer snapshot) {
        this.snapshot = snapshot;
    }

    public List<TnSdInfoDTO> getRecs() {
        return recs;
    }

    public void setRecs(List<TnSdInfoDTO> recs) {
        this.recs = recs;
    }
}
