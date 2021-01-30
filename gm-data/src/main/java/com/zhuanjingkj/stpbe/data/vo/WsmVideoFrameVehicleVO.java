package com.zhuanjingkj.stpbe.data.vo;

/**
 * 返回给客户端的WebSocket消息内容中的每一辆车
 */
public class WsmVideoFrameVehicleVO {
    private long wvfvvId;
    private long trackId;
    private int vehIdx;
    private String ppcxnk;
    private String hphm;
    private String cutImgUrl;
    private String crossTime;
    private String trafficViolationName;

    public WsmVideoFrameVehicleVO(long wvfvvId, long trackId, int vehIdx, String ppcxnk, String hphm,
                                  String cutImgUrl, String crossTime, String trafficViolationName) {
        this.wvfvvId = wvfvvId;
        this.trackId = trackId;
        this.vehIdx = vehIdx;
        this.ppcxnk = ppcxnk;
        this.hphm = hphm;
        this.cutImgUrl = cutImgUrl;
        this.crossTime = crossTime;
        this.trafficViolationName = trafficViolationName;
    }

    public long getWvfvvId() {
        return wvfvvId;
    }

    public void setWvfvvId(long wvfvvId) {
        this.wvfvvId = wvfvvId;
    }

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public int getVehIdx() {
        return vehIdx;
    }

    public void setVehIdx(int vehIdx) {
        this.vehIdx = vehIdx;
    }

    public String getPpcxnk() {
        return ppcxnk;
    }

    public void setPpcxnk(String ppcxnk) {
        this.ppcxnk = ppcxnk;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getCutImgUrl() {
        return cutImgUrl;
    }

    public void setCutImgUrl(String cutImgUrl) {
        this.cutImgUrl = cutImgUrl;
    }

    public String getCrossTime() {
        return crossTime;
    }

    public void setCrossTime(String crossTime) {
        this.crossTime = crossTime;
    }

    public String getTrafficViolationName() {
        return trafficViolationName;
    }

    public void setTrafficViolationName(String trafficViolationName) {
        this.trafficViolationName = trafficViolationName;
    }
}
