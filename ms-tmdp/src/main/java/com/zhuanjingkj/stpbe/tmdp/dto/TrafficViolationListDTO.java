package com.zhuanjingkj.stpbe.tmdp.dto;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.21
 **/
public class TrafficViolationListDTO extends BaseDTO {

    /**
     * 时间顺序交通违章列表
     */
    private List<TrafficViolationStatisticDTO> trafficViolationTimeFrameNumberList;

    /**
     * 不同类型交通违章列表
     */
    private List<TrafficViolationStatisticDTO> trafficViolationTypeNumberList;

    /**
     * 实时交通违章车辆信息列表
     */
    private List<TrafficViolationDTO> trafficViolationList;

    public List<TrafficViolationStatisticDTO> getTrafficViolationTimeFrameNumberList() {
        return trafficViolationTimeFrameNumberList;
    }

    public void setTrafficViolationTimeFrameNumberList(List<TrafficViolationStatisticDTO> trafficViolationTimeFrameNumberList) {
        this.trafficViolationTimeFrameNumberList = trafficViolationTimeFrameNumberList;
    }

    public List<TrafficViolationStatisticDTO> getTrafficViolationTypeNumberList() {
        return trafficViolationTypeNumberList;
    }

    public void setTrafficViolationTypeNumberList(List<TrafficViolationStatisticDTO> trafficViolationTypeNumberList) {
        this.trafficViolationTypeNumberList = trafficViolationTypeNumberList;
    }

    public List<TrafficViolationDTO> getTrafficViolationList() {
        return trafficViolationList;
    }

    public void setTrafficViolationList(List<TrafficViolationDTO> trafficViolationList) {
        this.trafficViolationList = trafficViolationList;
    }
}
