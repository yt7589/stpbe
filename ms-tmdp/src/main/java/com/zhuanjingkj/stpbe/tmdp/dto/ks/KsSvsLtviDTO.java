package com.zhuanjingkj.stpbe.tmdp.dto.ks;

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
    @JSONField(name = "cameraId")
    private long cameraId;
    @JSONField(name = "cameraName")
    private String cameraName;
    @JSONField(name = "hphm")
    private String hphm; // 号牌号码
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

    public KsSvsLtviDTO(int siteId, String siteName, String direction,
                        String type, long cameraId, String cameraName,
                        String hphm, int totalTvs, long imageId,
                        String imageUrl, double lng, double lat) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.direction = direction;
        this.type = type;
        this.cameraId = cameraId;
        this.cameraName = cameraName;
        this.hphm = hphm;
        this.totalTvs = totalTvs;
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.lng = lng;
        this.lat = lat;
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

    public long getCameraId() {
        return cameraId;
    }

    public void setCameraId(long cameraId) {
        this.cameraId = cameraId;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
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
}
