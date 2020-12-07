package com.zhuanjingkj.stpbe.data.entity;

/**
 * author by guoqiang
 * date on 2020.12.07
 **/
public class TrafficViolation {
    private Integer id;
    private String location;
    private String vehicleType;
    private String vehicleStyle;
    private Integer violationTypeId;
    private String trafficViolationTime;
    private String status;
    private Long imgId;
    private String vehiclePlate;
    private Integer isEmphasisVehicle;
    private String siteId;
    private Integer isNative;
    private String vehicleLogo;

    public Integer getIsEmphasisVehicle() {
        return isEmphasisVehicle;
    }

    public void setIsEmphasisVehicle(Integer isEmphasisVehicle) {
        this.isEmphasisVehicle = isEmphasisVehicle;
    }

    public Integer getIsNative() {
        return isNative;
    }

    public void setIsNative(Integer isNative) {
        this.isNative = isNative;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleStyle() {
        return vehicleStyle;
    }

    public void setVehicleStyle(String vehicleStyle) {
        this.vehicleStyle = vehicleStyle;
    }

    public Integer getViolationTypeId() {
        return violationTypeId;
    }

    public void setViolationTypeId(Integer violationTypeId) {
        this.violationTypeId = violationTypeId;
    }

    public String getTrafficViolationTime() {
        return trafficViolationTime;
    }

    public void setTrafficViolationTime(String trafficViolationTime) {
        this.trafficViolationTime = trafficViolationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }


    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }



    public String getVehicleLogo() {
        return vehicleLogo;
    }

    public void setVehicleLogo(String vehicleLogo) {
        this.vehicleLogo = vehicleLogo;
    }
}