package com.zhuanjingkj.zjcbe.domain.po;

public class VehicleAnalysisWztzPO {
    private int id;
    private String analysisId;
    private String createTime;
    private String clwz;
    private String psfx;
    private int vehicleNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getClwz() {
        return clwz;
    }

    public void setClwz(String clwz) {
        this.clwz = clwz;
    }

    public String getPsfx() {
        return psfx;
    }

    public void setPsfx(String psfx) {
        this.psfx = psfx;
    }

    public int getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(int vehicleNum) {
        this.vehicleNum = vehicleNum;
    }
}
