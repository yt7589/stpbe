package com.zhuanjingkj.stpbe.data.rto.vehicle;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

/**
 * author by guoqiang
 * date on 2020.12.01
 **/
public class VehicleInfoRTO extends BaseRTO {

    /**
     * 位置特征
     */
    @JSONField(name = "WZTZ")
    private PositionRTO position;

    /**
     * 顺序号
     */
    @JSONField(name = "SXH")
    private Integer OrderNumber;

    /**
     * 车型特征
     */
    @JSONField(name = "CXTZ")
    private VehicleModelRTO vehicleModel;

    /**
     * 车辆特征向量
     */
    @JSONField(name = "CLTZXL")
    private String vehicleVector;

    /**
     * 个性化特征
     */
    @JSONField(name = "GXHTZ")
    private IndividuationFeatureRTO individuationFeature;

    /**
     * 号牌特征
     */
    @JSONField(name = "HPTZ")
    private LicencePlateRTO licencePlate;

    /**
     * 驾驶行为特征
     */
    @JSONField(name = "JSXWTZ")
    private DrivingBehaviorRTO drivingBehavior;

    public PositionRTO getPosition() {
        return position;
    }

    public void setPosition(PositionRTO position) {
        this.position = position;
    }

    public Integer getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        OrderNumber = orderNumber;
    }

    public VehicleModelRTO getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModelRTO vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleVector() {
        return vehicleVector;
    }

    public void setVehicleVector(String vehicleVector) {
        this.vehicleVector = vehicleVector;
    }

    public IndividuationFeatureRTO getIndividuationFeature() {
        return individuationFeature;
    }

    public void setIndividuationFeature(IndividuationFeatureRTO individuationFeature) {
        this.individuationFeature = individuationFeature;
    }

    public LicencePlateRTO getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(LicencePlateRTO licencePlate) {
        this.licencePlate = licencePlate;
    }

    public DrivingBehaviorRTO getDrivingBehavior() {
        return drivingBehavior;
    }

    public void setDrivingBehavior(DrivingBehaviorRTO drivingBehavior) {
        this.drivingBehavior = drivingBehavior;
    }
}
