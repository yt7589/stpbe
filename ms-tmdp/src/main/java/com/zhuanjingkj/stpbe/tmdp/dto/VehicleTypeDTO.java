package com.zhuanjingkj.stpbe.tmdp.dto;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on 2020.11.20
 **/
public class VehicleTypeDTO extends BaseDTO {

    private Integer id;

    /**
     * 车辆类型名称
     */
    private String vehicleTypeName;

    /**
     * 车辆类型占比
     */
    private String vehicleTypePercentage;

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

    public String getVehicleTypePercentage() {
        return vehicleTypePercentage;
    }

    public void setVehicleTypePercentage(String vehicleTypePercentage) {
        this.vehicleTypePercentage = vehicleTypePercentage;
    }
}
