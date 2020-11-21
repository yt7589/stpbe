package com.zhuanjingkj.stpbe.tmdp.dto;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on 2020.11.21
 **/
public class TrafficViolationStatisticDTO extends BaseDTO {

    private String trafficViolationType;
    private Integer trafficViolationNum;
    private Integer timeFrame;

    public String getTrafficViolationType() {
        return trafficViolationType;
    }

    public void setTrafficViolationType(String trafficViolationType) {
        this.trafficViolationType = trafficViolationType;
    }

    public Integer getTrafficViolationNum() {
        return trafficViolationNum;
    }

    public void setTrafficViolationNum(Integer trafficViolationNum) {
        this.trafficViolationNum = trafficViolationNum;
    }

    public Integer getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(Integer timeFrame) {
        this.timeFrame = timeFrame;
    }
}
