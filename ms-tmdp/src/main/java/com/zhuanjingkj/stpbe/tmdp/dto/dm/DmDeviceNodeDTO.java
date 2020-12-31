package com.zhuanjingkj.stpbe.tmdp.dto.dm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DmDeviceNodeDTO extends BaseDTO {
    @JSONField(name = "deviceNodeId")
    private String deviceNodeId;
    @JSONField(name = "deviceNodeName")
    private String deviceNodeName;

    public DmDeviceNodeDTO(String deviceNodeId, String deviceNodeName) {
        this.deviceNodeId = deviceNodeId;
        this.deviceNodeName = deviceNodeName;
    }

    public String getDeviceNodeId() {
        return deviceNodeId;
    }

    public void setDeviceNodeId(String deviceNodeId) {
        this.deviceNodeId = deviceNodeId;
    }

    public String getDeviceNodeName() {
        return deviceNodeName;
    }

    public void setDeviceNodeName(String deviceNodeName) {
        this.deviceNodeName = deviceNodeName;
    }
}
