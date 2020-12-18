package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DkRtvrDTO extends BaseDTO {
    @JSONField(name = "id")
    private int id;
    @JSONField(name = "siteId")
    private int siteId;
    @JSONField(name = "siteName")
    private String siteName;
    @JSONField(name = "violationTypeId")
    private int violationTypeId;
    @JSONField(name = "violationTypeName")
    private String violationTypeName;
    @JSONField(name = "clpp")
    private String clpp; // 车辆品牌
    @JSONField(name = "ppcx")
    private String ppcx; // 品牌车型
    @JSONField(name = "hphm")
    private String hphm; // 号牌号码
    @JSONField(name = "occurTime")
    private String occurTime;
    @JSONField(name = "imgUrl")
    private String imgUrl;
    @JSONField(name = "imgId")
    private int imgId;

    public DkRtvrDTO(int id, int violationTypeId, String violationTypeName,
                     String clpp, String ppcx, String hphm, String occurTime,
                     int imgId, String imgUrl) {
        this.id = id;
        this.violationTypeId = violationTypeId;
        this.violationTypeName = violationTypeName;
        this.clpp = clpp;
        this.ppcx = ppcx;
        this.hphm = hphm;
        this.occurTime = occurTime;
        this.imgId = imgId;
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(String occurTime) {
        this.occurTime = occurTime;
    }

    public int getViolationTypeId() {
        return violationTypeId;
    }

    public void setViolationTypeId(int violationTypeId) {
        this.violationTypeId = violationTypeId;
    }

    public String getViolationTypeName() {
        return violationTypeName;
    }

    public void setViolationTypeName(String violationTypeName) {
        this.violationTypeName = violationTypeName;
    }

    public String getClpp() {
        return clpp;
    }

    public void setClpp(String clpp) {
        this.clpp = clpp;
    }

    public String getPpcx() {
        return ppcx;
    }

    public void setPpcx(String ppcx) {
        this.ppcx = ppcx;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
