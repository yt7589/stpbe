package com.zhuanjingkj.stpbe.data.vo;

public class CameraVehicleRecordVO {
    private long tvisJsonId;
    private int vehsIdx;
    private int sxh;
    private long trackId;
    private int x;
    private int y;
    private int w;
    private int h;
    private String orgImgFn;
    private String cutImgFn;

    public long getTvisJsonId() {
        return tvisJsonId;
    }

    public void setTvisJsonId(long tvisJsonId) {
        this.tvisJsonId = tvisJsonId;
    }

    public int getVehsIdx() {
        return vehsIdx;
    }

    public void setVehsIdx(int vehsIdx) {
        this.vehsIdx = vehsIdx;
    }

    public int getSxh() {
        return sxh;
    }

    public void setSxh(int sxh) {
        this.sxh = sxh;
    }

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public String getOrgImgFn() {
        return orgImgFn;
    }

    public void setOrgImgFn(String orgImgFn) {
        this.orgImgFn = orgImgFn;
    }

    public String getCutImgFn() {
        return cutImgFn;
    }

    public void setCutImgFn(String cutImgFn) {
        this.cutImgFn = cutImgFn;
    }

    public int getArea() {
        return w * h;
    }
}
