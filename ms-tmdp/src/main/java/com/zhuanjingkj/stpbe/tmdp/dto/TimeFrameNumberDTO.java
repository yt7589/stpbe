package com.zhuanjingkj.stpbe.tmdp.dto;

import com.netflix.discovery.DNSBasedAzToRegionMapper;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on
 **/
public class TimeFrameNumberDTO extends BaseDTO {

    Integer id;

    /**
     * 时间段
     */
    Integer timeFrame;

    /**
     * 日期
     */
    String date;

    /**
     * 车流量
     */
    Integer vehicleNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(Integer timeFrame) {
        this.timeFrame = timeFrame;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(Integer vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
