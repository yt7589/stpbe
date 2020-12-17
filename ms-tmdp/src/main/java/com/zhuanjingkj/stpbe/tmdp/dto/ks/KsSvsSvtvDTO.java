package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class KsSvsSvtvDTO extends BaseDTO {
    @JSONField(name = "seq")
    private int seq;
    @JSONField(name = "hphm")
    private String hphm;
    @JSONField(name = "ppcx")
    private String ppcx; // 品牌车型
    @JSONField(name = "trafficViolationTypeId")
    private int trafficViolationTypeId; // 交通违法类型编号
    @JSONField(name = "trafficViolationTypeName")
    private String trafficViolationTypeName; // 交通违法类型名称
    @JSONField(name = "siteId")
    private int siteId; // 点位编号
    @JSONField(name = "siteName")
    private String siteName; // 点位名称
    @JSONField(name = "shotTime")
    private String shotTime;

    public KsSvsSvtvDTO(int seq, String hphm, String ppcx, int trafficViolationTypeId,
                        String trafficViolationTypeName, int siteId, String siteName,
                        String shotTime) {
        this.seq = seq;
        this.hphm = hphm;
        this.ppcx = ppcx;
        this.trafficViolationTypeId = trafficViolationTypeId;
        this.trafficViolationTypeName = trafficViolationTypeName;
        this.siteId = siteId;
        this.siteName = siteName;
        this.shotTime = shotTime;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getPpcx() {
        return ppcx;
    }

    public void setPpcx(String ppcx) {
        this.ppcx = ppcx;
    }

    public int getTrafficViolationTypeId() {
        return trafficViolationTypeId;
    }

    public void setTrafficViolationTypeId(int trafficViolationTypeId) {
        this.trafficViolationTypeId = trafficViolationTypeId;
    }

    public String getTrafficViolationTypeName() {
        return trafficViolationTypeName;
    }

    public void setTrafficViolationTypeName(String trafficViolationTypeName) {
        this.trafficViolationTypeName = trafficViolationTypeName;
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

    public String getShotTime() {
        return shotTime;
    }

    public void setShotTime(String shotTime) {
        this.shotTime = shotTime;
    }
}
