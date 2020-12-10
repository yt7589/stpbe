package com.zhuanjingkj.stpbe.tmdp.dto.licencePlate;

import java.util.ArrayList;
import java.util.List;

/**
 * author by guoqiang
 * date on 2020.12.10
 **/
public class AbnormalLicencePlateListDTO {

    private List<TimeFrameAbnormalLicencePlateDTO> timeFrameAbnormalLicencePlateList;
    private List<RegionAbnormalLicencePlateDTO> regionAbnormalLicencePlateList;
    private List<AbnormalLicencePlateStatisticDTO> abnormalLicencePlateStatisticList;
    private List<AbnormalLicencePlateVehicleDTO> abnormalLicencePlateVehicleList;

    public List<TimeFrameAbnormalLicencePlateDTO> getTimeFrameAbnormalLicencePlateList() {
        return timeFrameAbnormalLicencePlateList;
    }

    public void setTimeFrameAbnormalLicencePlateList(List<TimeFrameAbnormalLicencePlateDTO> timeFrameAbnormalLicencePlateList) {
        this.timeFrameAbnormalLicencePlateList = timeFrameAbnormalLicencePlateList;
    }

    public List<RegionAbnormalLicencePlateDTO> getRegionAbnormalLicencePlateList() {
        return regionAbnormalLicencePlateList;
    }

    public void setRegionAbnormalLicencePlateList(List<RegionAbnormalLicencePlateDTO> regionAbnormalLicencePlateList) {
        this.regionAbnormalLicencePlateList = regionAbnormalLicencePlateList;
    }

    public List<AbnormalLicencePlateStatisticDTO> getAbnormalLicencePlateStatisticList() {
        return abnormalLicencePlateStatisticList;
    }

    public void setAbnormalLicencePlateStatisticList(List<AbnormalLicencePlateStatisticDTO> abnormalLicencePlateStatisticList) {
        this.abnormalLicencePlateStatisticList = abnormalLicencePlateStatisticList;
    }

    public List<AbnormalLicencePlateVehicleDTO> getAbnormalLicencePlateVehicleList() {
        return abnormalLicencePlateVehicleList;
    }

    public void setAbnormalLicencePlateVehicleList(List<AbnormalLicencePlateVehicleDTO> abnormalLicencePlateVehicleList) {
        this.abnormalLicencePlateVehicleList = abnormalLicencePlateVehicleList;
    }
}
