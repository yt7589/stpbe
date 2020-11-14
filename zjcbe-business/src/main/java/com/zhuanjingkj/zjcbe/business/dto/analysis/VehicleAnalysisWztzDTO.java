package com.zhuanjingkj.zjcbe.business.dto.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleAnalysisWztzDTO {

    @JsonProperty("PSFX")
    private String psfx;
    @JsonProperty("CLWZ")
    private String clwz;

    public String getClwz() {
        return clwz;
    }

    public void setClwz(String clwz) {
        this.clwz = clwz;
    }

    public String getPsfx() {

        switch (this.psfx) {

            case "1":
                return "车头";
            case "2":
                return "车尾";
            default:
                return "";
        }
    }

    public void setPsfx(String psfx) {
        this.psfx = psfx;
    }
}
