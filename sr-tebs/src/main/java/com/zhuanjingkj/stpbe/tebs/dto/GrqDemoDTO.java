package com.zhuanjingkj.stpbe.tebs.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class GrqDemoDTO extends BaseDTO {
    @JSONField(name = "grqId")
    private long grqId;
    @JSONField(name = "tvisJsonId")
    private long tvisJsonId;
    @JSONField(name = "vehsIdx")
    private long vehsIdx;
    @JSONField(name = "dist")
    private double dist;

    public GrqDemoDTO(long grqId, long tvisJsonId, long vehsIdx, double dist) {
        this.grqId = grqId;
        this.tvisJsonId = tvisJsonId;
        this.vehsIdx = vehsIdx;
        this.dist = dist;
    }

    public long getGrqId() {
        return grqId;
    }

    public void setGrqId(long grqId) {
        this.grqId = grqId;
    }

    public long getTvisJsonId() {
        return tvisJsonId;
    }

    public void setTvisJsonId(long tvisJsonId) {
        this.tvisJsonId = tvisJsonId;
    }

    public long getVehsIdx() {
        return vehsIdx;
    }

    public void setVehsIdx(long vehsIdx) {
        this.vehsIdx = vehsIdx;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }
}
