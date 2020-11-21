package com.zhuanjingkj.stpbe.tmdp.dto;

/**
 * author by guoqiang
 * date on 2020.11.20
 **/
public class VehicleTypeNumberDTO {

    private Integer id;
    private String vehicleTypeName;
    private Integer timeFrame;
    private Integer vehicleNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public Integer getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(Integer timeFrame) {
        this.timeFrame = timeFrame;
    }

    public Integer getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(Integer vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
