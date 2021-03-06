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
    @JSONField(name = "siteId")
    private long siteId;
    @JSONField(name = "deviceDirection")
    private String deviceDirection; //朝向
    @JSONField(name = "vehicleDirection")
    private String vehicleDirection; //车辆方向
    @JSONField(name = "videoUrl")
    private String videoUrl; //数据地址
    @JSONField(name = "cameraTypeId")
    private Integer cameraTypeId; //设备类型id
    @JSONField(name = "directionId")
    private Integer directionId; //方向id
    @JSONField(name = "scTypeId")
    private Integer scTypeId; //车辆方向id
    @JSONField(name = "streamId")
    private String streamId;

    public AddDeviceToDsRTO() {
        super();
    }

    public AddDeviceToDsRTO(long siteId, String deviceNo, String deviceType, String deviceNode, String deviceDirection,
                            String vehicleDirection, String videoUrl, Integer cameraTypeId,
                            Integer directionId, Integer scTypeId, String streamId) {
        this.deviceNo = deviceNo;
        this.deviceType = deviceType;
        this.deviceNode = deviceNode;
        this.deviceDirection = deviceDirection;
        this.vehicleDirection = vehicleDirection;
        this.videoUrl = videoUrl;
        this.siteId = siteId;
        this.cameraTypeId = cameraTypeId;
        this.directionId = directionId;
        this.scTypeId = scTypeId;
        this.streamId = streamId;
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public Integer getCameraTypeId() {
        return cameraTypeId;
    }

    public void setCameraTypeId(Integer cameraTypeId) {
        this.cameraTypeId = cameraTypeId;
    }

    public Integer getDirectionId() {
        return directionId;
    }

    public void setDirectionId(Integer directionId) {
        this.directionId = directionId;
    }

    public Integer getScTypeId() {
        return scTypeId;
    }

    public void setScTypeId(Integer scTypeId) {
        this.scTypeId = scTypeId;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }
}
