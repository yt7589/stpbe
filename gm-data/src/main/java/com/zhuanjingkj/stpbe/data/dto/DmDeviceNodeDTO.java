package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DmDeviceNodeDTO extends BaseDTO {
    @JSONField(name = "deviceNodeId")
    private long deviceNodeId;
    @JSONField(name = "deviceNodeName")
    private String deviceNodeName;

    public DmDeviceNodeDTO() {
        super();
    }

    public DmDeviceNodeDTO(long deviceNodeId, String deviceNodeName) {
        this.deviceNodeId = deviceNodeId;
        this.deviceNodeName = deviceNodeName;
    }

    public long getDeviceNodeId() {
        return deviceNodeId;
    }

    public void setDeviceNodeId(long deviceNodeId) {
        this.deviceNodeId = deviceNodeId;
    }

    public String getDeviceNodeName() {
        return deviceNodeName;
    }

    public void setDeviceNodeName(String deviceNodeName) {
        this.deviceNodeName = deviceNodeName;
    }
}
