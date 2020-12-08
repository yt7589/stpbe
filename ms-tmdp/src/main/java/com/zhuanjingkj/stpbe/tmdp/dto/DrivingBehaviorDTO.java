package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

/**
 * author by guoqiang
 * date on 2020.12.01
 **/
public class DrivingBehaviorDTO extends BaseDTO {

    /**
     * 主驾驶打电话
     */
    private Integer driverCalling;

    /**
     * 主驾驶看手机
     */
    private Integer driverReadPhone;

    /**
     * 主驾驶不及安全带
     */
    private Integer driverWithoutSeatBelt;

    /**
     * 主驾驶抽烟
     */
    private Integer driverSmoking;

    /**
     * 主驾驶放下遮阳板
     */
    private Integer driverDownSunShield;

    /**
     * 副驾驶不及安全带
     */
    private Integer copilotWithoutSeatBelt;

    /**
     * 副驾驶放下遮阳板
     */
    private Integer  copilotDownSunShield;

    /**
     * 起摩托不带头盔
     */
    private Integer withoutHelmet;

    public Integer getDriverCalling() {
        return driverCalling;
    }

    public void setDriverCalling(Integer driverCalling) {
        this.driverCalling = driverCalling;
    }

    public Integer getDriverReadPhone() {
        return driverReadPhone;
    }

    public void setDriverReadPhone(Integer driverReadPhone) {
        this.driverReadPhone = driverReadPhone;
    }

    public Integer getDriverWithoutSeatBelt() {
        return driverWithoutSeatBelt;
    }

    public void setDriverWithoutSeatBelt(Integer driverWithoutSeatBelt) {
        this.driverWithoutSeatBelt = driverWithoutSeatBelt;
    }

    public Integer getDriverSmoking() {
        return driverSmoking;
    }

    public void setDriverSmoking(Integer driverSmoking) {
        this.driverSmoking = driverSmoking;
    }

    public Integer getDriverDownSunShield() {
        return driverDownSunShield;
    }

    public void setDriverDownSunShield(Integer driverDownSunShield) {
        this.driverDownSunShield = driverDownSunShield;
    }

    public Integer getCopilotWithoutSeatBelt() {
        return copilotWithoutSeatBelt;
    }

    public void setCopilotWithoutSeatBelt(Integer copilotWithoutSeatBelt) {
        this.copilotWithoutSeatBelt = copilotWithoutSeatBelt;
    }

    public Integer getCopilotDownSunShield() {
        return copilotDownSunShield;
    }

    public void setCopilotDownSunShield(Integer copilotDownSunShield) {
        this.copilotDownSunShield = copilotDownSunShield;
    }

    public Integer getWithoutHelmet() {
        return withoutHelmet;
    }

    public void setWithoutHelmet(Integer withoutHelmet) {
        this.withoutHelmet = withoutHelmet;
    }
}
