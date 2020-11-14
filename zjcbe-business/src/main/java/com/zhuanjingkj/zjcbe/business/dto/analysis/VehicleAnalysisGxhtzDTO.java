package com.zhuanjingkj.zjcbe.business.dto.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleAnalysisGxhtzDTO {

    @JsonProperty("DCJQS")
    private String dcjqs;
    @JsonProperty("CCZTW")
    private String ccztw;
    @JsonProperty("GJ")
    private String gj;
    @JsonProperty("CSZT")
    private String cszt;
    @JsonProperty("XLJ")
    private String xlj;
    @JsonProperty("BJ")
    private String bj;
    @JsonProperty("CSCH")
    private String csch;
    @JsonProperty("CSPS")
    private String csps;
    @JsonProperty("CSGH")
    private String csgh;
    @JsonProperty("TC")
    private String tc;

    public String getCcztw() {
        return ccztw;
    }

    public void setCcztw(String ccztw) {
        this.ccztw = ccztw;
    }

    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }

    public String getGj() {
        return gj;
    }

    public void setGj(String gj) {
        this.gj = gj;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getXlj() {
        return xlj;
    }

    public void setXlj(String xlj) {
        this.xlj = xlj;
    }

    public String getDcjqs() {
        return dcjqs;
    }

    public void setDcjqs(String dcjqs) {
        this.dcjqs = dcjqs;
    }

    public String getCszt() {
        return cszt;
    }

    public void setCszt(String cszt) {
        this.cszt = cszt;
    }

    public String getCsps() {
        return csps;
    }

    public void setCsps(String csps) {
        this.csps = csps;
    }

    public String getCsgh() {
        return csgh;
    }

    public void setCsgh(String csgh) {
        this.csgh = csgh;
    }

    public String getCsch() {
        return csch;
    }

    public void setCsch(String csch) {
        this.csch = csch;
    }
}
