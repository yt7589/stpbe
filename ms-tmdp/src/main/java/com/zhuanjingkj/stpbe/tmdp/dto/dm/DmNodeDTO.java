package com.zhuanjingkj.stpbe.tmdp.dto.dm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DmNodeDTO extends BaseDTO {
    @JSONField(name = "nodeId")
    private long nodeId; //节点id
    @JSONField(name = "cityName")
    private String cityName; //所在城市
    @JSONField(name = "nodeName")
    private String nodeName; //节点名称
    @JSONField(name = "nodeAddr")
    private String nodeAddr; //节点位置
    @JSONField(name = "lng")
    private double lng; //节点经度
    @JSONField(name = "lat")
    private double lat; //节点纬度

    public DmNodeDTO(long nodeId, String cityName, String nodeName, String nodeAddr, double lng, double lat) {
        this.nodeId = nodeId;
        this.cityName = cityName;
        this.nodeName = nodeName;
        this.nodeAddr = nodeAddr;
        this.lng = lng;
        this.lat = lat;
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeAddr() {
        return nodeAddr;
    }

    public void setNodeAddr(String nodeAddr) {
        this.nodeAddr = nodeAddr;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
