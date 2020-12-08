package com.zhuanjingkj.stpbe.tmdp.dto;

/**
 * author by guoqiang
 * date on 2020.12.08
 **/
public class TrafficViolationTypeDTO {
    private String trafficViolationType;
    private Integer trafficViolationNum;

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
}
