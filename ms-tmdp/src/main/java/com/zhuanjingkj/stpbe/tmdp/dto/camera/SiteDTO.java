package com.zhuanjingkj.stpbe.tmdp.dto.camera;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on 2020.11.25
 **/
public class SiteDTO extends BaseDTO {
    private Integer siteId;
    private String siteName;

    /**
     * 经度
     */
    private String lng;

    /**
     * 纬度
     */
    private String lat;
    private Integer regionId;

    private Integer trafficViolationNum;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getTrafficViolationNum() {
        return trafficViolationNum;
    }

    public void setTrafficViolationNum(Integer trafficViolationNum) {
        this.trafficViolationNum = trafficViolationNum;
    }

    public Integer getId() {
        return siteId;
    }

    public void setId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
}
