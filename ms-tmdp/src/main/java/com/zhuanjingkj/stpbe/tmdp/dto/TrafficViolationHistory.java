package com.zhuanjingkj.stpbe.tmdp.dto;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.12.08
 **/
public class TrafficViolationHistory {
    private String licensePlate;
    private Integer totalTrafficViolationNum;
    private Integer currentYearTrafficViolationNum;
    private List<TrafficViolationTypeDTO> trafficViolationTypeList;
    private List<TrafficViolationHistoryNumDTO> trafficViolationHistoryNumList;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getTotalTrafficViolationNum() {
        return totalTrafficViolationNum;
    }

    public void setTotalTrafficViolationNum(Integer totalTrafficViolationNum) {
        this.totalTrafficViolationNum = totalTrafficViolationNum;
    }

    public Integer getCurrentYearTrafficViolationNum() {
        return currentYearTrafficViolationNum;
    }

    public void setCurrentYearTrafficViolationNum(Integer currentYearTrafficViolationNum) {
        this.currentYearTrafficViolationNum = currentYearTrafficViolationNum;
    }

    public List<TrafficViolationTypeDTO> getTrafficViolationTypeList() {
        return trafficViolationTypeList;
    }

    public void setTrafficViolationTypeList(List<TrafficViolationTypeDTO> trafficViolationTypeList) {
        this.trafficViolationTypeList = trafficViolationTypeList;
    }

    public List<TrafficViolationHistoryNumDTO> getTrafficViolationHistoryNumList() {
        return trafficViolationHistoryNumList;
    }

    public void setTrafficViolationHistoryNumList(List<TrafficViolationHistoryNumDTO> trafficViolationHistoryNumList) {
        this.trafficViolationHistoryNumList = trafficViolationHistoryNumList;
    }
}
