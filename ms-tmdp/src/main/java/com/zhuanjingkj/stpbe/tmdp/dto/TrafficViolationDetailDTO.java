package com.zhuanjingkj.stpbe.tmdp.dto;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on 2020.12.08
 **/
public class TrafficViolationDetailDTO extends BaseDTO {
    private String date;
    private String location;
    private String isNative;
    private String vehiclePlate;
    private String trafficViolationType;
    private String vehicleType;
    private String vehicleSubType;
    private DrivingBehaviorDTO drivingBehavior;
    private String vehicleColor;
    private VehicleBrandDTO vehicleBrand;

    private LicencePlateDTO licencePlate;

    public LicencePlateDTO getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(LicencePlateDTO licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIsNative() {
        return isNative;
    }

    public void setIsNative(String isNative) {
        this.isNative = isNative;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public String getTrafficViolationType() {
        return trafficViolationType;
    }

    public void setTrafficViolationType(String trafficViolationType) {
        this.trafficViolationType = trafficViolationType;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleSubType() {
        return vehicleSubType;
    }

    public void setVehicleSubType(String vehicleSubType) {
        this.vehicleSubType = vehicleSubType;
    }

    public DrivingBehaviorDTO getDrivingBehavior() {
        return drivingBehavior;
    }

    public void setDrivingBehavior(DrivingBehaviorDTO drivingBehavior) {
        this.drivingBehavior = drivingBehavior;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public VehicleBrandDTO getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(VehicleBrandDTO vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }
}
