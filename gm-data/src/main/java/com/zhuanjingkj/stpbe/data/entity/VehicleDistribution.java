package com.zhuanjingkj.stpbe.data.entity;

/**
 * author by guoqiang
 * date on 2020.12.04
 **/
public class VehicleDistribution {

    private Integer id;
    private String vehicleDistributionName;
    private Long vehicleDistributionNum;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleDistributionName() {
        return vehicleDistributionName;
    }

    public void setVehicleDistributionName(String vehicleDistributionName) {
        this.vehicleDistributionName = vehicleDistributionName;
    }

    public Long getVehicleDistributionNum() {
        return vehicleDistributionNum;
    }

    public void setVehicleDistributionNum(Long vehicleDistributionNum) {
        this.vehicleDistributionNum = vehicleDistributionNum;
    }
}
