package com.zhuanjingkj.zjcbe.business.dto.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleAnalysisHptzDTO {

    @JsonProperty("YWLSHP")
    private String ywlshp;
    @JsonProperty("HPZT")
    private String hpzt;
    @JsonProperty("HPYS")
    private String hpys;
    @JsonProperty("HPKXD")
    private String hpkxd;
    @JsonProperty("MWHPKXD")
    private String mwhpkxd;
    @JsonProperty("HPGG")
    private String hpgg;
    @JsonProperty("HPWZ")
    private String hpwz;
    @JsonProperty("HPZL")
    private String hpzl;
    @JsonProperty("HPHM")
    private String hphm;

    public String getYwlshp() {
        return ywlshp == "1" ? "有" : "无";
    }

    public void setYwlshp(String ywlshp) {
        this.ywlshp = ywlshp;
    }

    public String getHpzt() {

        switch (this.hpzt) {

            case "0":
                return "未悬挂号牌";
            case "1":
                return "正常号牌";
            case "2":
                return "遮挡号牌";
            case "3":
                return "污损号牌";
            case "9":
                return "其他";
            default:
                return "";
        }
    }

    public void setHpzt(String hpzt) {
        this.hpzt = hpzt;
    }

    public String getHpys() {
        return hpys;
    }

    public void setHpys(String hpys) {
        this.hpys = hpys;
    }

    public String getHpkxd() {
        return hpkxd;
    }

    public void setHpkxd(String hpkxd) {
        this.hpkxd = hpkxd;
    }

    public String getMwhpkxd() {
        return mwhpkxd;
    }

    public void setMwhpkxd(String mwhpkxd) {
        this.mwhpkxd = mwhpkxd;
    }

    public String getHpgg() {
        switch (this.hpgg) {

            case "1":
                return "字符单排排列";
            case "2":
                return "字符上下两排排列";
            default:
                return "";
        }
    }

    public void setHpgg(String hpgg) {
        this.hpgg = hpgg;
    }

    public String getHpwz() {
        return hpwz;
    }

    public void setHpwz(String hpwz) {
        this.hpwz = hpwz;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }
}
