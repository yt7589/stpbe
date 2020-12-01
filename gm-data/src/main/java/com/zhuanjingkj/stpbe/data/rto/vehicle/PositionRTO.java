package com.zhuanjingkj.stpbe.data.rto.vehicle;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

/**
 * author by guoqiang
 * date on 2020.12.01
 **/
public class PositionRTO extends BaseRTO {

    /**
     * 车辆位置
     */
    @JSONField(name = "CLWZ")
    private String vehiclePosition;

    /**
     * 拍摄角度
     */
    @JSONField(name = "PSFX")
    private String shootingAngle;

    public String getVehiclePosition() {
        return vehiclePosition;
    }

    public void setVehiclePosition(String vehiclePosition) {
        this.vehiclePosition = vehiclePosition;
    }

    public String getShootingAngle() {
        return shootingAngle;
    }

    public void setShootingAngle(String shootingAngle) {
        this.shootingAngle = shootingAngle;
    }
}
