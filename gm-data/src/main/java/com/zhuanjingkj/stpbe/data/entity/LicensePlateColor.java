package com.zhuanjingkj.stpbe.data.entity;

/**
 * author by guoqiang
 * date on 2020.12.05
 **/
public class LicensePlateColor {
    private Integer id;
    private String licensePlateColorName;
    private String licensePlateColorCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicensePlateColorName() {
        return licensePlateColorName;
    }

    public void setLicensePlateColorName(String licensePlateColorName) {
        this.licensePlateColorName = licensePlateColorName;
    }

    public String getLicensePlateColorCode() {
        return licensePlateColorCode;
    }

    public void setLicensePlateColorCode(String licensePlateColorCode) {
        this.licensePlateColorCode = licensePlateColorCode;
    }
}
