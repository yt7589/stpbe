package com.zhuanjingkj.stpbe.platform.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class TrafficFlowDTO extends BaseDTO {
    @JSONField(name = "todayTraffics")
    private List<TrafficFlowItemDTO> todayTraffics;
    @JSONField(name = "yesterdayTraffics")
    private List<TrafficFlowItemDTO> yesterdayTraffics;

    public List<TrafficFlowItemDTO> getTodayTraffics() {
        return todayTraffics;
    }

    public void setTodayTraffics(List<TrafficFlowItemDTO> todayTraffics) {
        this.todayTraffics = todayTraffics;
    }

    public List<TrafficFlowItemDTO> getYesterdayTraffics() {
        return yesterdayTraffics;
    }

    public void setYesterdayTraffics(List<TrafficFlowItemDTO> yesterdayTraffics) {
        this.yesterdayTraffics = yesterdayTraffics;
    }
}
