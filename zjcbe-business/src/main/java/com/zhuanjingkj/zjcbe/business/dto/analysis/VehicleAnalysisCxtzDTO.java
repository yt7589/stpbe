package com.zhuanjingkj.zjcbe.business.dto.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleAnalysisCxtzDTO {

    @JsonProperty("PPCX")
    private String ppcx;
    @JsonProperty("CLLXZFL")
    private String cllxzfl;
    @JsonProperty("PPXHMS")
    private String ppxhms;
    @JsonProperty("CSYS")
    private String csys;
    @JsonProperty("CLPP")
    private String clpp;
    @JsonProperty("PPXHKXD")
    private String ppxhkxd;
    @JsonProperty("CLLXFL")
    private String cllxfl;
    @JsonProperty("CXNK")
    private String cxnk;

    public String getPpcx() {
        return ppcx;
    }

    public void setPpcx(String ppcx) {
        this.ppcx = ppcx;
    }

    public String getCllxfl() {
        return cllxfl;
    }

    public void setCllxfl(String cllxfl) {
        this.cllxfl = cllxfl;
    }

    public String getCllxzfl() {
        return cllxzfl;
    }

    public void setCllxzfl(String cllxzfl) {
        this.cllxzfl = cllxzfl;
    }

    public String getPpxhms() {
        return ppxhms;
    }

    public void setPpxhms(String ppxhms) {
        this.ppxhms = ppxhms;
    }

    public String getCsys() {
        return csys;
    }

    public void setCsys(String csys) {
        this.csys = csys;
    }

    public String getClpp() {
        return clpp;
    }

    public void setClpp(String clpp) {
        this.clpp = clpp;
    }

    public String getPpxhkxd() {
        return ppxhkxd;
    }

    public void setPpxhkxd(String ppxhkxd) {
        this.ppxhkxd = ppxhkxd;
    }

    public String getCxnk() {
        return cxnk;
    }

    public void setCxnk(String cxnk) {
        this.cxnk = cxnk;
    }
}
