package com.zhuanjingkj.stpbe.tmdp.dto;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.20
 **/


public class VehicleStatisticListDTO extends BaseDTO {

    List<VehicleDistributionDTO> vehicleDistributionList;

    List<VehicleTypeDTO> vehicleTypeList;

    List<TimeFrameNumberDTO> timeFrameNumberList;

    List<VehicleTypeNumberDTO> vehicleTypeNumberList;

    List<VehicleStatisticDTO> streetList;

    List<VehicleStatisticDTO> regionList;

    VehiclePassedNumberDTO vehiclePassedNumber;

    public List<VehicleDistributionDTO> getVehicleDistributionList() {
        return vehicleDistributionList;
    }

    public void setVehicleDistributionList(List<VehicleDistributionDTO> vehicleDistributionList) {
        this.vehicleDistributionList = vehicleDistributionList;
    }

    public List<VehicleTypeDTO> getVehicleTypeDTOList() {
        return vehicleTypeList;
    }

    public void setVehicleTypeList(List<VehicleTypeDTO> vehicleTypeList) {
        this.vehicleTypeList = vehicleTypeList;
    }

    public List<TimeFrameNumberDTO> getTimeFrameNumberList() {
        return timeFrameNumberList;
    }

    public void setTimeFrameNumberList(List<TimeFrameNumberDTO> timeFrameNumberList) {
        this.timeFrameNumberList = timeFrameNumberList;
    }

    public List<VehicleTypeNumberDTO> getVehicleTypeNumberList() {
        return vehicleTypeNumberList;
    }

    public void setVehicleTypeNumberList(List<VehicleTypeNumberDTO> vehicleTypeNumberList) {
        this.vehicleTypeNumberList = vehicleTypeNumberList;
    }

    public List<VehicleStatisticDTO> getStreetList() {
        return streetList;
    }

    public void setStreetList(List<VehicleStatisticDTO> streetList) {
        this.streetList = streetList;
    }

    public List<VehicleStatisticDTO> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<VehicleStatisticDTO> regionList) {
        this.regionList = regionList;
    }

    public VehiclePassedNumberDTO getVehiclePassedNumber() {
        return vehiclePassedNumber;
    }

    public void setVehiclePassedNumber(VehiclePassedNumberDTO vehiclePassedNumber) {
        this.vehiclePassedNumber = vehiclePassedNumber;
    }
}
