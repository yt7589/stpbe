package com.zhuanjingkj.stpbe.tvis_server.wxs2102.dto;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class IdrAnalysisItemDTO extends BaseDTO {
    private long tvisJsonId;
    private long vehIdx;
    private String cutImageUrl;
    private String hphm;
    private String occurTime;
    private String trafficViolationName;

    public IdrAnalysisItemDTO(long tvisJsonId, long vehIdx, String cutImageUrl,
                              String hphm, String occurTime, String trafficViolationName) {
        this.tvisJsonId = tvisJsonId;
        this.vehIdx = vehIdx;
        this.cutImageUrl = cutImageUrl;
        this.hphm = hphm;
        this.occurTime = occurTime;
        this.trafficViolationName = trafficViolationName;
    }

    public long getTvisJsonId() {
        return tvisJsonId;
    }

    public void setTvisJsonId(long tvisJsonId) {
        this.tvisJsonId = tvisJsonId;
    }

    public long getVehIdx() {
        return vehIdx;
    }

    public void setVehIdx(long vehIdx) {
        this.vehIdx = vehIdx;
    }

    public String getCutImageUrl() {
        return cutImageUrl;
    }

    public void setCutImageUrl(String cutImageUrl) {
        this.cutImageUrl = cutImageUrl;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(String occurTime) {
        this.occurTime = occurTime;
    }

    public String getTrafficViolationName() {
        return trafficViolationName;
    }

    public void setTrafficViolationName(String trafficViolationName) {
        this.trafficViolationName = trafficViolationName;
    }
}
