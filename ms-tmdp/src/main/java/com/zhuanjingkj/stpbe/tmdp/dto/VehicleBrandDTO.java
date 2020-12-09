package com.zhuanjingkj.stpbe.tmdp.dto;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on 2020.12.08
 **/
public class VehicleBrandDTO extends BaseDTO {

    private String vehicleBrand;
    private String vehicleBrandType;
    private String vehicleYear;
    private String credibility;

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleBrandType() {
        return vehicleBrandType;
    }

    public void setVehicleBrandType(String vehicleBrandType) {
        this.vehicleBrandType = vehicleBrandType;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getCredibility() {
        return credibility;
    }

    public void setCredibility(String credibility) {
        this.credibility = credibility;
    }
}
