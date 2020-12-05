package com.zhuanjingkj.stpbe.data.entity;

/**
 * author by guoqiang
 * date on 2020.12.05
 **/
public class VehicleSubType {
    private Integer id;
    private String vehicleSubTypeName;
    private String vehicleSubTypeCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleSubTypeName() {
        return vehicleSubTypeName;
    }

    public void setVehicleSubTypeName(String vehicleSubTypeName) {
        this.vehicleSubTypeName = vehicleSubTypeName;
    }

    public String getVehicleSubTypeCode() {
        return vehicleSubTypeCode;
    }

    public void setVehicleSubTypeCode(String vehicleSubTypeCode) {
        this.vehicleSubTypeCode = vehicleSubTypeCode;
    }
}
