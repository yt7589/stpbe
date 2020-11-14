package com.zhuanjingkj.zjcbe.business.dto.analysis;

import com.zhuanjingkj.zjcbe.commondata.enums.VehicleResourceEnum;

public class VehicleResourceDTO {

    private VehicleResourceEnum resouceType;

    private String resouceUrl;

    private String resoucePath;

    public VehicleResourceEnum getResouceType() {
        return resouceType;
    }

    public void setResouceType(VehicleResourceEnum resouceType) {
        this.resouceType = resouceType;
    }

    public String getResouceUrl() {
        return resouceUrl;
    }

    public void setResouceUrl(String resouceUrl) {
        this.resouceUrl = resouceUrl;
    }

    public String getResoucePath() {
        return resoucePath;
    }

    public void setResoucePath(String resoucePath) {
        this.resoucePath = resoucePath;
    }
}
