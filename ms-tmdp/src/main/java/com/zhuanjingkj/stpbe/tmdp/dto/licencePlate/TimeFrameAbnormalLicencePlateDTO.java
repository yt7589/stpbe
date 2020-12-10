package com.zhuanjingkj.stpbe.tmdp.dto.licencePlate;

/**
 * author by guoqiang
 * date on 2020.12.09
 **/
public class TimeFrameAbnormalLicencePlateDTO {
    private String timeFrame;
    private Integer abnormalLicencePlateNum;

    public String getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(String timeFrame) {
        this.timeFrame = timeFrame;
    }

    public Integer getAbnormalLicencePlateNum() {
        return abnormalLicencePlateNum;
    }

    public void setAbnormalLicencePlateNum(Integer abnormalLicencePlateNum) {
        this.abnormalLicencePlateNum = abnormalLicencePlateNum;
    }
}
