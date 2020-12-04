package com.zhuanjingkj.stpbe.data.entity.image;

import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

/**
 * author by guoqiang
 * date on 2020.12.03
 **/
public class VehicleInformation {
    private Long id;
    private String uid;

    /**
     * 位置特征
     */
    private String positionInfo;

    /**
     * 顺序号
     */
    private Integer orderNumber;

    /**
     * 车型特征
     */
    private String vehicleModelInfo;

    /**
     * 车辆特征向量
     */
    private String vehicleVector;

    /**
     * 个性化特征
     */
    private String individuationInfo;

    /**
     * 号牌特征
     */
    private String licencePlate;

    /**
     * 驾驶行为特征
     */
    private String drivingBehavior;

    /**
     * 图片ID
     */
    private Long imageId;

    /**
     * 图片表名称
     */
    private String imageTableName;

    /**
     * 创建时间
     */
    private String creatTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPositionInfo() {
        return positionInfo;
    }

    public void setPositionInfo(String positionInfo) {
        this.positionInfo = positionInfo;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getVehicleModelInfo() {
        return vehicleModelInfo;
    }

    public void setVehicleModelInfo(String vehicleModelInfo) {
        this.vehicleModelInfo = vehicleModelInfo;
    }

    public String getVehicleVector() {
        return vehicleVector;
    }

    public void setVehicleVector(String vehicleVector) {
        this.vehicleVector = vehicleVector;
    }

    public String getIndividuationInfo() {
        return individuationInfo;
    }

    public void setIndividuationInfo(String individuationInfo) {
        this.individuationInfo = individuationInfo;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getDrivingBehavior() {
        return drivingBehavior;
    }

    public void setDrivingBehavior(String drivingBehavior) {
        this.drivingBehavior = drivingBehavior;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageTableName() {
        return imageTableName;
    }

    public void setImageTableName(String imageTableName) {
        this.imageTableName = imageTableName;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }
}
