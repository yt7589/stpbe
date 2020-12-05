package com.zhuanjingkj.stpbe.data.entity;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.12.05
 **/
public class VehicleFeatureList {

    private List<LicensePlateColor> licensePlateColorList;
    private List<LicensePlateType> licensePlateTypeList;
    private List<VehicleColor> vehicleColorList;
    private List<VehicleDisplayGoods> vehicleDisplayGoodsList;
    private List<VehicleSubType> vehicleSubTypeList;
    private List<VehicleType> vehicleTypeList;
    private List<VehicleWindowPasteColor> vehicleWindowPasteColorList;

    public List<LicensePlateColor> getLicensePlateColorList() {
        return licensePlateColorList;
    }

    public void setLicensePlateColorList(List<LicensePlateColor> licensePlateColorList) {
        this.licensePlateColorList = licensePlateColorList;
    }

    public List<LicensePlateType> getLicensePlateTypeList() {
        return licensePlateTypeList;
    }

    public void setLicensePlateTypeList(List<LicensePlateType> licensePlateTypeList) {
        this.licensePlateTypeList = licensePlateTypeList;
    }

    public List<VehicleColor> getVehicleColorList() {
        return vehicleColorList;
    }

    public void setVehicleColorList(List<VehicleColor> vehicleColorList) {
        this.vehicleColorList = vehicleColorList;
    }

    public List<VehicleDisplayGoods> getVehicleDisplayGoodsList() {
        return vehicleDisplayGoodsList;
    }

    public void setVehicleDisplayGoodsList(List<VehicleDisplayGoods> vehicleDisplayGoodsList) {
        this.vehicleDisplayGoodsList = vehicleDisplayGoodsList;
    }

    public List<VehicleSubType> getVehicleSubTypeList() {
        return vehicleSubTypeList;
    }

    public void setVehicleSubTypeList(List<VehicleSubType> vehicleSubTypeList) {
        this.vehicleSubTypeList = vehicleSubTypeList;
    }

    public List<VehicleType> getVehicleTypeList() {
        return vehicleTypeList;
    }

    public void setVehicleTypeList(List<VehicleType> vehicleTypeList) {
        this.vehicleTypeList = vehicleTypeList;
    }

    public List<VehicleWindowPasteColor> getVehicleWindowPasteColorList() {
        return vehicleWindowPasteColorList;
    }

    public void setVehicleWindowPasteColorList(List<VehicleWindowPasteColor> vehicleWindowPasteColorList) {
        this.vehicleWindowPasteColorList = vehicleWindowPasteColorList;
    }
}
