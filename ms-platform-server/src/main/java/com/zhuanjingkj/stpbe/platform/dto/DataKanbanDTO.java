package com.zhuanjingkj.stpbe.platform.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DataKanbanDTO extends BaseDTO {
    @JSONField(name = "trafficFlow")
    private TrafficFlowDTO trafficFlow;

    public TrafficFlowDTO getTrafficFlow() {
        return trafficFlow;
    }

    public void setTrafficFlow(TrafficFlowDTO trafficFlow) {
        this.trafficFlow = trafficFlow;
    }
}
