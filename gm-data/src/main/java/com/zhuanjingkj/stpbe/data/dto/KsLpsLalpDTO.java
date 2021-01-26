package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class KsLpsLalpDTO extends BaseDTO {
    @JSONField(name = "tvisJsonId")
    private long tvisJsonId;
    @JSONField(name = "tvisJsonTbl")
    private String tvisJsonTbl;
    @JSONField(name = "gcxh")
    private long gcxh; // 过车序号（原始图片）
    @JSONField(name = "sxh")
    private long sxh; // 全局顺序号（小图序号）
    @JSONField(name = "siteId")
    private long siteId;
    @JSONField(name = "siteName")
    private String siteName;
    @JSONField(name = "cccurTime")
    private String occurTime;
    @JSONField(name = "hphm")
    private String hphm;
    @JSONField(name = "totalTimes")
    private int totalTimes;
    @JSONField(name = "imageId")
    private int imageId;
    @JSONField(name = "imageUrl")
    private String imageUrl;

    public KsLpsLalpDTO() {
        super();
    }

    public KsLpsLalpDTO(long gcxh, long sxh, long siteId, String siteName, String occurTime, String hphm, int totalTimes, int imageId, String imageUrl) {
        this.gcxh = gcxh;
        this.sxh = sxh;
        this.siteId = siteId;
        this.siteName = siteName;
        this.occurTime = occurTime;
        this.hphm = hphm;
        this.totalTimes = totalTimes;
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }

    public long getGcxh() {
        return gcxh;
    }

    public void setGcxh(long gcxh) {
        this.gcxh = gcxh;
    }

    public long getSxh() {
        return sxh;
    }

    public void setSxh(long sxh) {
        this.sxh = sxh;
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

    public String getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(String occurTime) {
        this.occurTime = occurTime;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public int getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(int totalTimes) {
        this.totalTimes = totalTimes;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getTvisJsonId() {
        return tvisJsonId;
    }

    public void setTvisJsonId(long tvisJsonId) {
        this.tvisJsonId = tvisJsonId;
    }

    public String getTvisJsonTbl() {
        return tvisJsonTbl;
    }

    public void setTvisJsonTbl(String tvisJsonTbl) {
        this.tvisJsonTbl = tvisJsonTbl;
    }
}
