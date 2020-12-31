package com.zhuanjingkj.stpbe.tmdp.dto.dc;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 数据中心 =》 数据统计
 * 今日概况
 */
public class DcStTodayDTO extends BaseDTO {
    private Integer vehicle; //过车总量
    private Integer vehicle_city; //本市车辆
    private Integer vehicle_town; //外埠车辆
    private Integer vehicle_city_act; //本市车辆活跃度
    private Integer device_online; //本市设备在线率

    public DcStTodayDTO(Integer vehicle, Integer vehicle_city, Integer vehicle_town, Integer vehicle_city_act, Integer device_online) {
        this.vehicle = vehicle;
        this.vehicle_city = vehicle_city;
        this.vehicle_town = vehicle_town;
        this.vehicle_city_act = vehicle_city_act;
        this.device_online = device_online;
    }

    public Integer getVehicle() {
        return vehicle;
    }

    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getVehicle_city() {
        return vehicle_city;
    }

    public void setVehicle_city(Integer vehicle_city) {
        this.vehicle_city = vehicle_city;
    }

    public Integer getVehicle_town() {
        return vehicle_town;
    }

    public void setVehicle_town(Integer vehicle_town) {
        this.vehicle_town = vehicle_town;
    }

    public Integer getVehicle_city_act() {
        return vehicle_city_act;
    }

    public void setVehicle_city_act(Integer vehicle_city_act) {
        this.vehicle_city_act = vehicle_city_act;
    }

    public Integer getDevice_online() {
        return device_online;
    }

    public void setDevice_online(Integer device_online) {
        this.device_online = device_online;
    }
}
