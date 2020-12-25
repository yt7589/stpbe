package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class KsVcLsvsDTO extends BaseDTO {
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

    public KsVcLsvsDTO(long gcxh, long sxh, long siteId, String siteName, String occurTime, String hphm, int totalTimes) {
        this.gcxh = gcxh;
        this.sxh = sxh;
        this.siteId = siteId;
        this.siteName = siteName;
        this.occurTime = occurTime;
        this.hphm = hphm;
        this.totalTimes = totalTimes;
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
}
