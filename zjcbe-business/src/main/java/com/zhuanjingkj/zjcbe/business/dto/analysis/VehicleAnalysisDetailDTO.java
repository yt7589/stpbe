package com.zhuanjingkj.zjcbe.business.dto.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

public class VehicleAnalysisDetailDTO {

    @JsonProperty("SXH")
    @ApiModelProperty("顺序号")
    private int sxh;

    @JsonProperty("CXTZ")
    @ApiModelProperty("车型特征")
    private VehicleAnalysisCxtzDTO cxtz;

    @JsonProperty("GXHTZ")
    @ApiModelProperty("个性化特征")
    private VehicleAnalysisGxhtzDTO gxhtz;

    @JsonProperty("HPTZ")
    @ApiModelProperty("号牌特征")
    private VehicleAnalysisHptzDTO hptz;

    @JsonProperty("JSXWTZ")
    @ApiModelProperty("驾驶行为特征")
    private VehicleAnalysisJsxwtzDTO jsxztz;

    @JsonProperty("WZTZ")
    @ApiModelProperty("位置特征")
    private VehicleAnalysisWztzDTO wztz;

    @JsonProperty("thumbImage")
    @ApiModelProperty("车辆小图")
    private String thumbImage;

    public int getSxh() {
        return sxh;
    }

    public void setSxh(int sxh) {
        this.sxh = sxh;
    }

    public VehicleAnalysisCxtzDTO getCxtz() {
        return cxtz;
    }

    public void setCxtz(VehicleAnalysisCxtzDTO cxtz) {
        this.cxtz = cxtz;
    }

    public VehicleAnalysisGxhtzDTO getGxhtz() {
        return gxhtz;
    }

    public void setGxhtz(VehicleAnalysisGxhtzDTO gxhtz) {
        this.gxhtz = gxhtz;
    }

    public VehicleAnalysisHptzDTO getHptz() {
        return hptz;
    }

    public void setHptz(VehicleAnalysisHptzDTO hptz) {
        this.hptz = hptz;
    }

    public VehicleAnalysisJsxwtzDTO getJsxztz() {
        return jsxztz;
    }

    public void setJsxztz(VehicleAnalysisJsxwtzDTO jsxztz) {
        this.jsxztz = jsxztz;
    }

    public VehicleAnalysisWztzDTO getWztz() {
        return wztz;
    }

    public void setWztz(VehicleAnalysisWztzDTO wztz) {
        this.wztz = wztz;
    }

    public String getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }
}
