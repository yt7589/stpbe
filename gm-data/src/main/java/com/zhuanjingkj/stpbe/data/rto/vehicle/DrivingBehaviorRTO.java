package com.zhuanjingkj.stpbe.data.rto.vehicle;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

/**
 * author by guoqiang
 * date on 2020.12.01
 **/
public class DrivingBehaviorRTO extends BaseRTO {

    /**
     * 主驾驶打电话
     */
    @JSONField(name = "ZJSDDH")
    private String driverCalling;

    /**
     * 主驾驶看手机
     */
    @JSONField(name = "ZJSKSJ")
    private String driverReadPhone;

    /**
     * 主驾驶不及安全带
     */
    @JSONField(name = "ZJSBJAQD")
    private String driverWithoutSeatBelt;

    /**
     * 主驾驶抽烟
     */
    @JSONField(name = "ZJSCY")
    private String driverSmoking;

    /**
     * 主驾驶放下遮阳板
     */
    @JSONField(name = "ZJSZYB")
    private String driverDownSunShield;

    /**
     * 副驾驶不及安全带
     */
    @JSONField(name = "FJSBJAQD")
    private String copilotWithoutSeatBelt;

    /**
     * 副驾驶放下遮阳板
     */
    @JSONField(name = "FJSZYB")
    private String  copilotDownSunShield;

    /**
     * 起摩托不带头盔
     */
    @JSONField(name = "MTCBDTK")
    private String withoutHelmet;

    public String getDriverCalling() {
        return driverCalling;
    }

    public void setDriverCalling(String driverCalling) {
        this.driverCalling = driverCalling;
    }

    public String getDriverReadPhone() {
        return driverReadPhone;
    }

    public void setDriverReadPhone(String driverReadPhone) {
        this.driverReadPhone = driverReadPhone;
    }

    public String getDriverWithoutSeatBelt() {
        return driverWithoutSeatBelt;
    }

    public void setDriverWithoutSeatBelt(String driverWithoutSeatBelt) {
        this.driverWithoutSeatBelt = driverWithoutSeatBelt;
    }

    public String getDriverSmoking() {
        return driverSmoking;
    }

    public void setDriverSmoking(String driverSmoking) {
        this.driverSmoking = driverSmoking;
    }

    public String getDriverDownSunShield() {
        return driverDownSunShield;
    }

    public void setDriverDownSunShield(String driverDownSunShield) {
        this.driverDownSunShield = driverDownSunShield;
    }

    public String getCopilotWithoutSeatBelt() {
        return copilotWithoutSeatBelt;
    }

    public void setCopilotWithoutSeatBelt(String copilotWithoutSeatBelt) {
        this.copilotWithoutSeatBelt = copilotWithoutSeatBelt;
    }

    public String getCopilotDownSunShield() {
        return copilotDownSunShield;
    }

    public void setCopilotDownSunShield(String copilotDownSunShield) {
        this.copilotDownSunShield = copilotDownSunShield;
    }

    public String getWithoutHelmet() {
        return withoutHelmet;
    }

    public void setWithoutHelmet(String withoutHelmet) {
        this.withoutHelmet = withoutHelmet;
    }
}
