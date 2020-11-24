package com.zhuanjingkj.stpbe.tmdp.dto;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on 2020.11.21
 **/
public class TrafficViolationDTO extends BaseDTO {

    private Long id;
    private String location;
    private String vehicleType;
    private String vehicleStyle;
    private Long violationTypeId;
    private String trafficViolationTime;
    private String status;
    private Long imgVaId;
    private Long imgId;
    private String vehiclePlate;
    private String imgUrl;
    private String imgVaUrl;
    private String violationTypeName;
    private String siteName;
    private String lng;
    private String lat;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getViolationTypeId() {
        return violationTypeId;
    }

    public void setViolationTypeId(Long violationTypeId) {
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

    public Long getImgVaId() {
        return imgVaId;
    }

    public void setImgVaId(Long imgVaId) {
        this.imgVaId = imgVaId;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgVaUrl() {
        return imgVaUrl;
    }

    public void setImgVaUrl(String imgVaUrl) {
        this.imgVaUrl = imgVaUrl;
    }

    public String getViolationTypeName() {
        return violationTypeName;
    }

    public void setViolationTypeName(String violationTypeName) {
        this.violationTypeName = violationTypeName;
    }
}
