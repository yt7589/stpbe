package com.zhuanjingkj.stpbe.platform.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class TrafficFlowItemDTO extends BaseDTO {
    @JSONField(name = "count")
    private int count;
    @JSONField(name = "reportTime")
    private String reportTime;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }
}
