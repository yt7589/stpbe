package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class SmSysInfoDTO extends BaseDTO {
    @JSONField(name = "sysId")
    private Integer sysId;
    @JSONField(name = "qyName")
    private String qyName;
    @JSONField(name = "qyImgUrl")
    private String qyImgUrl;
    @JSONField(name = "sysName")
    private String sysName;
    @JSONField(name = "qyIcp")
    private String qyIcp;
    @JSONField(name = "ownership")
    private String ownership;
    @JSONField(name = "city")
    private String city;

    public SmSysInfoDTO(Integer sysId, String qyName, String qyImgUrl, String sysName, String qyIcp,
                        String ownership, String city) {
        this.sysId = sysId;
        this.qyName = qyName;
        this.qyImgUrl = qyImgUrl;
        this.sysName = sysName;
        this.qyIcp = qyIcp;
        this.ownership = ownership;
        this.city = city;
    }

    public Integer getSysId() {
        return sysId;
    }

    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    public String getQyName() {
        return qyName;
    }

    public void setQyName(String qyName) {
        this.qyName = qyName;
    }

    public String getQyImgUrl() {
        return qyImgUrl;
    }

    public void setQyImgUrl(String qyImgUrl) {
        this.qyImgUrl = qyImgUrl;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getQyIcp() {
        return qyIcp;
    }

    public void setQyIcp(String qyIcp) {
        this.qyIcp = qyIcp;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
