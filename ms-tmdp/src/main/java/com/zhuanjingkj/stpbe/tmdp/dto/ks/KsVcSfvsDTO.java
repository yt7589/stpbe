package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class KsVcSfvsDTO extends BaseDTO {
    @JSONField(name = "siteId")
    private long siteId;
    @JSONField(name = "siteName")
    private String siteName;
    @JSONField(name = "totalTimes")
    private int totalTimes;
    @JSONField(name = "hphm")
    private String hphm;
    @JSONField(name = "lng")
    private Double lng;
    @JSONField(name = "lat")
    private Double lat;

    public KsVcSfvsDTO(long siteId, String siteName, Double lng, Double lat, int totalTimes, String hphm) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.lng = lng;
        this.lat = lat;
        this.totalTimes = totalTimes;
        this.hphm = hphm;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public int getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(int totalTimes) {
        this.totalTimes = totalTimes;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }
}
