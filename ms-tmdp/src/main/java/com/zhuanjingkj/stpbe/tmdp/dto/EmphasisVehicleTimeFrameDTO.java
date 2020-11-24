package com.zhuanjingkj.stpbe.tmdp.dto;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on 2020.11.23
 **/
public class EmphasisVehicleTimeFrameDTO extends BaseDTO {

    private String date;
    private Integer emphasisVehicleNum;
    private Integer timeFrame;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getEmphasisVehicleNum() {
        return emphasisVehicleNum;
    }

    public void setEmphasisVehicleNum(Integer emphasisVehicleNum) {
        this.emphasisVehicleNum = emphasisVehicleNum;
    }

    public Integer getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(Integer timeFrame) {
        this.timeFrame = timeFrame;
    }
}
