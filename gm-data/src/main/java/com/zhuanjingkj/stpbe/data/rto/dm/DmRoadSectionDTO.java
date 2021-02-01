package com.zhuanjingkj.stpbe.data.rto.dm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DmRoadSectionDTO extends BaseDTO {
    @JSONField(name = "rssId")
    private long rssId; //路段id
    @JSONField(name = "rssName")
    private String rssName; //路段名称
    @JSONField(name = "rssAddr")
    private String rssAddr; //路段位置
    @JSONField(name = "srcSiteName")
    private String srcSiteName; //路段开始点位名称
    @JSONField(name = "srcSiteLng")
    private double srcSiteLng; //路段开始点位经度
    @JSONField(name = "srcSiteLat")
    private double srcSiteLat; //路段开始点位纬度
    @JSONField(name = "dstSiteName")
    private String dstSiteName; //路段结束点位名称
    @JSONField(name = "dstSiteLng")
    private double dstSiteLng; //路段结束点位经度
    @JSONField(name = "dstSiteLat")
    private double dstSiteLat; //路段结束点位纬度
    @JSONField(name = "level")
    private String level; //路段级别 0：普通路段 1：重点路段

    public DmRoadSectionDTO() {
        super();
    }

    public DmRoadSectionDTO(long rssId, String rssName, String rssAddr, String srcSiteName, double srcSiteLng, double srcSiteLat,
                            String dstSiteName, double dstSiteLng, double dstSiteLat, String level) {
        this.rssId = rssId;
        this.rssName = rssName;
        this.rssAddr = rssAddr;
        this.srcSiteName = srcSiteName;
        this.srcSiteLng = srcSiteLng;
        this.srcSiteLat = srcSiteLat;
        this.dstSiteName = dstSiteName;
        this.dstSiteLng = dstSiteLng;
        this.dstSiteLat = dstSiteLat;
        this.level = level;
    }

    public long getRssId() {
        return rssId;
    }

    public void setRssId(long rssId) {
        this.rssId = rssId;
    }

    public String getRssName() {
        return rssName;
    }

    public void setRssName(String rssName) {
        this.rssName = rssName;
    }

    public String getRssAddr() {
        return rssAddr;
    }

    public void setRssAddr(String rssAddr) {
        this.rssAddr = rssAddr;
    }

    public String getSrcSiteName() {
        return srcSiteName;
    }

    public void setSrcSiteName(String srcSiteName) {
        this.srcSiteName = srcSiteName;
    }

    public double getSrcSiteLng() {
        return srcSiteLng;
    }

    public void setSrcSiteLng(double srcSiteLng) {
        this.srcSiteLng = srcSiteLng;
    }

    public double getSrcSiteLat() {
        return srcSiteLat;
    }

    public void setSrcSiteLat(double srcSiteLat) {
        this.srcSiteLat = srcSiteLat;
    }

    public String getDstSiteName() {
        return dstSiteName;
    }

    public void setDstSiteName(String dstSiteName) {
        this.dstSiteName = dstSiteName;
    }

    public double getDstSiteLng() {
        return dstSiteLng;
    }

    public void setDstSiteLng(double dstSiteLng) {
        this.dstSiteLng = dstSiteLng;
    }

    public double getDstSiteLat() {
        return dstSiteLat;
    }

    public void setDstSiteLat(double dstSiteLat) {
        this.dstSiteLat = dstSiteLat;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
