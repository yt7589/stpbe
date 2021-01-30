package com.zhuanjingkj.stpbe.data.rto.dm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class AddDeviceToDsRTO extends BaseRTO {
    @JSONField(name = "deviceId")
    private long deviceId;
    @JSONField(name = "deviceNo")
    private String deviceNo; //设备编号
    @JSONField(name = "deviceType")
    private String deviceType; //设备类型
    @JSONField(name = "deviceNode")
    private String deviceNode; //所属节点
    @JSONField(name = "deviceDirection")
    private String deviceDirection; //朝向
    @JSONField(name = "vehicleDirection")
    private String vehicleDirection; //车辆方向
    @JSONField(name = "dtUrl")
    private String dtUrl; //数据地址

    public AddDeviceToDsRTO() {
        super();
    }

    public AddDeviceToDsRTO(String deviceNo, String deviceType, String deviceNode, String deviceDirection, String vehicleDirection, String dtUrl) {
        this.deviceNo = deviceNo;
        this.deviceType = deviceType;
        this.deviceNode = deviceNode;
        this.deviceDirection = deviceDirection;
        this.vehicleDirection = vehicleDirection;
        this.dtUrl = dtUrl;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceNode() {
        return deviceNode;
    }

    public void setDeviceNode(String deviceNode) {
        this.deviceNode = deviceNode;
    }

    public String getDeviceDirection() {
        return deviceDirection;
    }

    public void setDeviceDirection(String deviceDirection) {
        this.deviceDirection = deviceDirection;
    }

    public String getVehicleDirection() {
        return vehicleDirection;
    }

    public void setVehicleDirection(String vehicleDirection) {
        this.vehicleDirection = vehicleDirection;
    }

    public String getDtUrl() {
        return dtUrl;
    }

    public void setDtUrl(String dtUrl) {
        this.dtUrl = dtUrl;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }
}
