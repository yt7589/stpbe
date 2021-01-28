package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 数据中心 =》 数据统计
 * 点位前5
 */
public class DcStVSiteDTO extends BaseDTO {
    @JSONField(name = "siteId")
    private long siteId;
    @JSONField(name = "siteName")
    private String siteName;
    @JSONField(name = "lng")
    private double lng;
    @JSONField(name = "lat")
    private double lat;
    @JSONField(name = "count")
    private int count;

    public DcStVSiteDTO() {
        super();
    }

    public DcStVSiteDTO(long siteId, String siteName, double lng, double lat, Integer count) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.lng = lng;
        this.lat = lat;
        this.count = count;
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

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
