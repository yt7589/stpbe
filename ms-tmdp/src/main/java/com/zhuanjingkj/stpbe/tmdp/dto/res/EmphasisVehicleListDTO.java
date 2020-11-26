package com.zhuanjingkj.stpbe.tmdp.dto.res;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.emphavehi.EmphasisVehicleImgUrl;
import com.zhuanjingkj.stpbe.tmdp.dto.emphavehi.EmphasisVehicleInformationDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.emphavehi.EmphasisVehicleNumberDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.emphavehi.EmphasisVehicleTimeFrameDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.vehiinfo.TrafficViolationDTO;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.11.24
 **/
public class EmphasisVehicleListDTO extends BaseDTO {

    private List<EmphasisVehicleInformationDTO> emphasisVehiclePercentageList;
    private List<EmphasisVehicleInformationDTO> regionEmphasisVehicleList;

    private List<EmphasisVehicleInformationDTO> siteEmphasisVehicleList;

    private List<TrafficViolationDTO> emphasisVehicleViolationList;

    private List<EmphasisVehicleTimeFrameDTO> EmphasisVehicleTimeFrameList;

    private EmphasisVehicleNumberDTO emphasisVehicleNumber;

    private List<EmphasisVehicleImgUrl> emphasisVehicleImgUrlList;

    public List<EmphasisVehicleImgUrl> getEmphasisVehicleImgUrlList() {
        return emphasisVehicleImgUrlList;
    }

    public void setEmphasisVehicleImgUrlList(List<EmphasisVehicleImgUrl> emphasisVehicleImgUrlList) {
        this.emphasisVehicleImgUrlList = emphasisVehicleImgUrlList;
    }

    public List<EmphasisVehicleInformationDTO> getEmphasisVehiclePercentageList() {
        return emphasisVehiclePercentageList;
    }

    public void setEmphasisVehiclePercentageList(List<EmphasisVehicleInformationDTO> emphasisVehiclePercentageList) {
        this.emphasisVehiclePercentageList = emphasisVehiclePercentageList;
    }

    public List<EmphasisVehicleInformationDTO> getRegionEmphasisVehicleList() {
        return regionEmphasisVehicleList;
    }

    public void setRegionEmphasisVehicleList(List<EmphasisVehicleInformationDTO> regionEmphasisVehicleList) {
        this.regionEmphasisVehicleList = regionEmphasisVehicleList;
    }

    public List<EmphasisVehicleInformationDTO> getSiteEmphasisVehicleList() {
        return siteEmphasisVehicleList;
    }

    public void setSiteEmphasisVehicleList(List<EmphasisVehicleInformationDTO> siteEmphasisVehicleList) {
        this.siteEmphasisVehicleList = siteEmphasisVehicleList;
    }

    public List<TrafficViolationDTO> getEmphasisVehicleViolationList() {
        return emphasisVehicleViolationList;
    }

    public void setEmphasisVehicleViolationList(List<TrafficViolationDTO> emphasisVehicleViolationList) {
        this.emphasisVehicleViolationList = emphasisVehicleViolationList;
    }

    public List<EmphasisVehicleTimeFrameDTO> getEmphasisVehicleTimeFrameList() {
        return EmphasisVehicleTimeFrameList;
    }

    public void setEmphasisVehicleTimeFrameList(List<EmphasisVehicleTimeFrameDTO> emphasisVehicleTimeFrameList) {
        EmphasisVehicleTimeFrameList = emphasisVehicleTimeFrameList;
    }

    public EmphasisVehicleNumberDTO getEmphasisVehicleNumber() {
        return emphasisVehicleNumber;
    }

    public void setEmphasisVehicleNumber(EmphasisVehicleNumberDTO emphasisVehicleNumber) {
        this.emphasisVehicleNumber = emphasisVehicleNumber;
    }
}
