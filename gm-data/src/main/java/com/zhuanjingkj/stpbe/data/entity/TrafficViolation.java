package com.zhuanjingkj.stpbe.data.entity;

/**
 * author by guoqiang
 * date on 2020.12.07
 **/
public class TrafficViolation {
    private Integer id;
    private String location;
    private String vehicleTypeName;
    private String vehicleStyle;
    private Integer violationTypeId;
    private String trafficViolationTime;
    private String status;
    private Long imgId;
    private String vehiclePlate;
    private Integer isEmphasisVehicle;
    private String siteId;
    private Integer isNative;
    private String vehicleBrand;
    private String vehicleSubTypeName;

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

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getVehicleSubTypeName() {
        return vehicleSubTypeName;
    }

    public void setVehicleSubTypeName(String vehicleSubTypeName) {
        this.vehicleSubTypeName = vehicleSubTypeName;
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

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }
}
