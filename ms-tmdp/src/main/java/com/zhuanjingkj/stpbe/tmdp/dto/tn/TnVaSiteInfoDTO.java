package com.zhuanjingkj.stpbe.tmdp.dto.tn;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.TnVaSdInfoDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 点位信息
 */
public class TnVaSiteInfoDTO extends BaseDTO {
    @JSONField(name = "camera")
    private Integer camera; //摄像头数量
    @JSONField(name = "snapshot")
    private Integer snapshot; //抓拍机数量
    @JSONField(name = "recs")
    List<TnVaSdInfoDTO> recs = new ArrayList<>();

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

    public List<TnVaSdInfoDTO> getRecs() {
        return recs;
    }

    public void setRecs(List<TnVaSdInfoDTO> recs) {
        this.recs = recs;
    }
}
