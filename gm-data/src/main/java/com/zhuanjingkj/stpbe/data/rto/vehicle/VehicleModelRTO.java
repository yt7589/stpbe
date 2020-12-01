package com.zhuanjingkj.stpbe.data.rto.vehicle;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

/**
 * author by guoqiang
 * date on 2020.12.01
 **/
public class VehicleModelRTO extends BaseRTO {

    /**
     * 车辆类型
     */
    @JSONField(name = "CLLXFL")
    private String vehicleType;

    /**
     * 车辆类型子分类
     */
    @JSONField(name = "CLLXZFL")
    private String vehicleTypeSub;

    /**
     * 车身颜色
     */
    @JSONField(name = "CSYS")
    private String vehicleColor;

    /**
     * 车辆品牌
     */
    @JSONField(name = "CLPP")
    private String vehicleBrand;

    /**
     * 品牌车型
     */
    @JSONField(name = "PPCX")
    private String BrandModel;

    /**
     * 车型年款
     */
    @JSONField(name = "CXNK")
    private String vehicleYear;

    /**
     * 品牌车型描述
     */
    @JSONField(name = "PPXHMS")
    private String brandModelDescribe;

    /**
     * 品牌型号可信度
     */
    @JSONField(name = "PPXHKXD")
    private String brandModelCredibility;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleTypeSub() {
        return vehicleTypeSub;
    }

    public void setVehicleTypeSub(String vehicleTypeSub) {
        this.vehicleTypeSub = vehicleTypeSub;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getBrandModel() {
        return BrandModel;
    }

    public void setBrandModel(String brandModel) {
        BrandModel = brandModel;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getBrandModelDescribe() {
        return brandModelDescribe;
    }

    public void setBrandModelDescribe(String brandModelDescribe) {
        this.brandModelDescribe = brandModelDescribe;
    }

    public String getBrandModelCredibility() {
        return brandModelCredibility;
    }

    public void setBrandModelCredibility(String brandModelCredibility) {
        this.brandModelCredibility = brandModelCredibility;
    }
}
