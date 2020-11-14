package com.zhuanjingkj.zjcbe.business.dto.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleAnalysisJsxwtzDTO {

    @JsonProperty("ZJSKSJ")
    private String zjsksj;
    @JsonProperty("MTCBDTK")
    private String mtcbdtk;
    @JsonProperty("ZJSBJAQD")
    private String zjsbjaqd;
    @JsonProperty("ZJSDDH")
    private String zjsddh;
    @JsonProperty("FJSZYB")
    private String fjszyb;
    @JsonProperty("FJSBJAQD")
    private String fjsbjaqd;
    @JsonProperty("ZJSCY")
    private String zjscy;
    @JsonProperty("ZJSZYB")
    private String zjszyb;

    public String getZjsksj() {
        return zjsksj;
    }

    public void setZjsksj(String zjsksj) {
        this.zjsksj = zjsksj;
    }

    public String getMtcbdtk() {
        return mtcbdtk;
    }

    public void setMtcbdtk(String mtcbdtk) {
        this.mtcbdtk = mtcbdtk;
    }

    public String getZjsbjaqd() {
        return zjsbjaqd;
    }

    public void setZjsbjaqd(String zjsbjaqd) {
        this.zjsbjaqd = zjsbjaqd;
    }

    public String getZjsddh() {
        return zjsddh;
    }

    public void setZjsddh(String zjsddh) {
        this.zjsddh = zjsddh;
    }

    public String getFjszyb() {
        return fjszyb;
    }

    public void setFjszyb(String fjszyb) {
        this.fjszyb = fjszyb;
    }

    public String getFjsbjaqd() {
        return fjsbjaqd;
    }

    public void setFjsbjaqd(String fjsbjaqd) {
        this.fjsbjaqd = fjsbjaqd;
    }

    public String getZjscy() {
        return zjscy;
    }

    public void setZjscy(String zjscy) {
        this.zjscy = zjscy;
    }

    public String getZjszyb() {
        return zjszyb;
    }

    public void setZjszyb(String zjszyb) {
        this.zjszyb = zjszyb;
    }
}
