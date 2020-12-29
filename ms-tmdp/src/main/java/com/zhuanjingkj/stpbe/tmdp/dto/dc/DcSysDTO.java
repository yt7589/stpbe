package com.zhuanjingkj.stpbe.tmdp.dto.dc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DcSysDTO extends BaseDTO {
    @JSONField(name = "device")
    private Integer device; //设备数量
    @JSONField(name = "control")
    private Integer control; //布控数量
    @JSONField(name = "alerts")
    private Integer alerts; //警报数
    @JSONField(name = "keyVehicle")
    private Integer keyVehicle; //重点车辆
    @JSONField(name = "ils")
    private Integer ils; //违法总数 illegal
    @JSONField(name = "truck")
    private Integer truck; //大货车总数

    public DcSysDTO(Integer device, Integer control, Integer alerts, Integer keyVehicle, Integer ils, Integer truck) {
        this.device = device;
        this.control = control;
        this.alerts = alerts;
        this.keyVehicle = keyVehicle;
        this.ils = ils;
        this.truck = truck;
    }

    public Integer getDevice() {
        return device;
    }

    public void setDevice(Integer device) {
        this.device = device;
    }

    public Integer getControl() {
        return control;
    }

    public void setControl(Integer control) {
        this.control = control;
    }

    public Integer getAlerts() {
        return alerts;
    }

    public void setAlerts(Integer alerts) {
        this.alerts = alerts;
    }

    public Integer getKeyVehicle() {
        return keyVehicle;
    }

    public void setKeyVehicle(Integer keyVehicle) {
        this.keyVehicle = keyVehicle;
    }

    public Integer getIls() {
        return ils;
    }

    public void setIls(Integer ils) {
        this.ils = ils;
    }

    public Integer getTruck() {
        return truck;
    }

    public void setTruck(Integer truck) {
        this.truck = truck;
    }
}
