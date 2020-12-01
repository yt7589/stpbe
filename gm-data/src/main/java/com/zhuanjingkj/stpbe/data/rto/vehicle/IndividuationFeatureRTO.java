package com.zhuanjingkj.stpbe.data.rto.vehicle;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

/**
 * author by guoqiang
 * date on 2020.12.01
 **/
public class IndividuationFeatureRTO extends BaseRTO {

    /**
     * 车窗粘贴物
     */
    @JSONField(name="CCZTW")
    private String windowPaste;

    /**
     * 摆件
     */
    @JSONField(name="BJ")
    private String ornaments;

    /**
     * 挂件
     */
    @JSONField(name="GJ")
    private String pendant;

    /**
     * 天窗
     */
    @JSONField(name="TC")
    private String sunRoof;

    /**
     * 行李架
     */
    @JSONField(name="XLJ")
    private String  baggageHolder;

    /**
     * 倒车镜缺失
     */
    @JSONField(name="DCJQS")
    private String rearviewMiss;

    /**
     * 车身张贴
     */
    @JSONField(name="CSZT")
    private String vehiclePaste;

    /**
     * 车身破损
     */
    @JSONField(name="CSPS")
    private String vehicleBroken;

    /**
     * 车身刮痕
     */
    @JSONField(name="CSGH")
    private String vehicleScratch;

    /**
     * 车身彩绘
     */
    @JSONField(name="CSCH")
    private String vehicleDrawing;

    public String getWindowPaste() {
        return windowPaste;
    }

    public void setWindowPaste(String windowPaste) {
        this.windowPaste = windowPaste;
    }

    public String getOrnaments() {
        return ornaments;
    }

    public void setOrnaments(String ornaments) {
        this.ornaments = ornaments;
    }

    public String getPendant() {
        return pendant;
    }

    public void setPendant(String pendant) {
        this.pendant = pendant;
    }

    public String getSunRoof() {
        return sunRoof;
    }

    public void setSunRoof(String sunRoof) {
        this.sunRoof = sunRoof;
    }

    public String getBaggageHolder() {
        return baggageHolder;
    }

    public void setBaggageHolder(String baggageHolder) {
        this.baggageHolder = baggageHolder;
    }

    public String getRearviewMiss() {
        return rearviewMiss;
    }

    public void setRearviewMiss(String rearviewMiss) {
        this.rearviewMiss = rearviewMiss;
    }

    public String getVehiclePaste() {
        return vehiclePaste;
    }

    public void setVehiclePaste(String vehiclePaste) {
        this.vehiclePaste = vehiclePaste;
    }

    public String getVehicleBroken() {
        return vehicleBroken;
    }

    public void setVehicleBroken(String vehicleBroken) {
        this.vehicleBroken = vehicleBroken;
    }

    public String getVehicleScratch() {
        return vehicleScratch;
    }

    public void setVehicleScratch(String vehicleScratch) {
        this.vehicleScratch = vehicleScratch;
    }

    public String getVehicleDrawing() {
        return vehicleDrawing;
    }

    public void setVehicleDrawing(String vehicleDrawing) {
        this.vehicleDrawing = vehicleDrawing;
    }
}
