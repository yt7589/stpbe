package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class KsSvsLtviDTO extends BaseDTO {
    @JSONField(name = "siteId")
    private int siteId;
    @JSONField(name = "siteName")
    private String siteName;
    @JSONField(name = "direction")
    private String direction; // 朝向
    @JSONField(name = "type")
    private String type; // 车头车尾
    @JSONField(name = "violationTypeId")
    private int violationTypeId;
    @JSONField(name = "violationTypeName")
    private String violationTypeName;
    @JSONField(name = "hphm")
    private String hphm; // 号牌号码
    @JSONField(name = "clpp")
    private String clpp;
    @JSONField(name = "totalTvs")
    private int totalTvs; // 累计违章数量
    @JSONField(name = "imageId")
    private long imageId;
    @JSONField(name = "imageUrl")
    private String imageUrl;
    @JSONField(name = "lng")
    private double lng; // 精度
    @JSONField(name = "lat")
    private double lat; // 纬度
    @JSONField(name = "occurTime")
    private String occurTime;
    @JSONField(name = "imageHash")
    private String imageHash;

    public KsSvsLtviDTO() {
        super();
    }

    public KsSvsLtviDTO(int siteId, String siteName, String direction,
                        String type, int violationTypeId, String violationTypeName,
                        String hphm, String clpp, int totalTvs, long imageId,
                        String imageUrl, double lng, double lat, String occurTime) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.direction = direction;
        this.type = type;
        this.violationTypeId = violationTypeId;
        this.violationTypeName = violationTypeName;
        this.hphm = hphm;
        this.clpp = clpp;
        this.totalTvs = totalTvs;
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.lng = lng;
        this.lat = lat;
        this.occurTime = occurTime;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getClpp() {
        return clpp;
    }

    public void setClpp(String clpp) {
        this.clpp = clpp;
    }

    public int getTotalTvs() {
        return totalTvs;
    }

    public void setTotalTvs(int totalTvs) {
        this.totalTvs = totalTvs;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(String occurTime) {
        this.occurTime = occurTime;
    }

    public String getImageHash() {
        return imageHash;
    }

    public void setImageHash(String imageHash) {
        this.imageHash = imageHash;
    }
}
