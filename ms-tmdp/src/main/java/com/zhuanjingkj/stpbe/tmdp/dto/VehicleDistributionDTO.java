package com.zhuanjingkj.stpbe.tmdp.dto;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on
 **/
public class VehicleDistributionDTO extends BaseDTO {
    private Integer id;
    private String vehicleDistributionName;
    private String vehicleDistributionPercentage;

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

    public String getVehicleDistributionPercntage() {
        return vehicleDistributionPercentage;
    }

    public void setVehicleDistributionPercntage(String vehicleDistributionPercntage) {
        this.vehicleDistributionPercentage = vehicleDistributionPercntage;
    }
}
