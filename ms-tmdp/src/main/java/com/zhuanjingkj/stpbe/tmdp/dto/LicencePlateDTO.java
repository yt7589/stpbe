package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

/**
 * author by guoqiang
 * date on 2020.12.01
 **/
public class LicencePlateDTO extends BaseDTO {

    /**
     * 号牌状态
     */
    private String licencePlateStatus;

    /**
     * 号牌位置
     */
    private String licencePlatePosition;

    /**
     * 号牌种类
     */
    private String licencePlateType;

    /**
     * 号牌颜色样式
     */
    private String licencePlatColorStyle;

    /**
     * 号牌字符样式
     */
    private String licencePlateCharSetStyle;

    /**
     * 号牌号码
     */
    private String licencePlateNumber;

    /**
     * 号牌可信度
     */
    private String licencePlateReliability;

    /**
     * 每位号牌可信度
     */
    private String eachLicencePlateReliability;

    /**
     * 有无临时号牌
     */
    private Integer tempLicencePlate;

    public String getLicencePlateStatus() {
        return licencePlateStatus;
    }

    public void setLicencePlateStatus(String licencePlateStatus) {
        this.licencePlateStatus = licencePlateStatus;
    }

    public String getLicencePlatePosition() {
        return licencePlatePosition;
    }

    public void setLicencePlatePosition(String licencePlatePosition) {
        this.licencePlatePosition = licencePlatePosition;
    }

    public String getLicencePlateType() {
        return licencePlateType;
    }

    public void setLicencePlateType(String licencePlateType) {
        this.licencePlateType = licencePlateType;
    }

    public String getLicencePlatColorStyle() {
        return licencePlatColorStyle;
    }

    public void setLicencePlatColorStyle(String licencePlatColorStyle) {
        this.licencePlatColorStyle = licencePlatColorStyle;
    }

    public String getLicencePlateCharSetStyle() {
        return licencePlateCharSetStyle;
    }

    public void setLicencePlateCharSetStyle(String licencePlateCharSetStyle) {
        this.licencePlateCharSetStyle = licencePlateCharSetStyle;
    }

    public String getLicencePlateNumber() {
        return licencePlateNumber;
    }

    public void setLicencePlateNumber(String licencePlateNumber) {
        this.licencePlateNumber = licencePlateNumber;
    }

    public String getLicencePlateReliability() {
        return licencePlateReliability;
    }

    public void setLicencePlateReliability(String licencePlateReliability) {
        this.licencePlateReliability = licencePlateReliability;
    }

    public String getEachLicencePlateReliability() {
        return eachLicencePlateReliability;
    }

    public void setEachLicencePlateReliability(String eachLicencePlateReliability) {
        this.eachLicencePlateReliability = eachLicencePlateReliability;
    }

    public Integer getTempLicencePlate() {
        return tempLicencePlate;
    }

    public void setTempLicencePlate(Integer tempLicencePlate) {
        this.tempLicencePlate = tempLicencePlate;
    }

}
