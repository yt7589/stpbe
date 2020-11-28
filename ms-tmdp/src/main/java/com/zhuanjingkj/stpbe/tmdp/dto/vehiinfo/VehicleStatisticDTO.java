package com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on 2020.11.20
 **/
public class VehicleStatisticDTO extends BaseDTO {

    private Integer id;

    /**
     * 地区名称
     */
    private String regionName;

    /**
     * 路段名称或街区名称
     */
    private String streetName;

    /**
     * 日期
     */
    private String date;

    /**
     * 车流量
     */
    private Integer passedNumber = 0;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPassedNumber() {
        return passedNumber;
    }

    public void setPassedNumber(Integer passedNumber) {
        this.passedNumber = passedNumber;
    }
}
