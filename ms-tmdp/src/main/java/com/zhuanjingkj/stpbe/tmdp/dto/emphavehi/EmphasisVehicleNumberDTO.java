package com.zhuanjingkj.stpbe.tmdp.dto.emphavehi;

/**
 * author by guoqiang
 * date on 2020.11.24
 **/
public class EmphasisVehicleNumberDTO {
    private Integer currentEmphasisVehicleNum;
    private Integer alarmNum;
    private Integer emphasisVehicleEmphasisRegionNum;
    private Integer onlineDevice;

    public Integer getOnlineDevice() {
        return onlineDevice;
    }

    public void setOnlineDevice(Integer onlineDevice) {
        this.onlineDevice = onlineDevice;
    }

    public Integer getCurrentEmphasisVehicleNum() {
        return currentEmphasisVehicleNum;
    }

    public void setCurrentEmphasisVehicleNum(Integer currentEmphasisVehicleNum) {
        this.currentEmphasisVehicleNum = currentEmphasisVehicleNum;
    }

    public Integer getAlarmNum() {
        return alarmNum;
    }

    public void setAlarmNum(Integer alarmNum) {
        this.alarmNum = alarmNum;
    }

    public Integer getEmphasisVehicleEmphasisRegionNum() {
        return emphasisVehicleEmphasisRegionNum;
    }

    public void setEmphasisVehicleEmphasisRegionNum(Integer emphasisVehicleEmphasisRegionNum) {
        this.emphasisVehicleEmphasisRegionNum = emphasisVehicleEmphasisRegionNum;
    }
}
