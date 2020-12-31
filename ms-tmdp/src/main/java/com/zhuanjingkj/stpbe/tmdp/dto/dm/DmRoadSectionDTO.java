package com.zhuanjingkj.stpbe.tmdp.dto.dm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DmRoadSectionDTO extends BaseDTO {
    @JSONField(name = "rssId")
    private long rssId; //路段id
    @JSONField(name = "rssName")
    private String rssName; //路段名称
    @JSONField(name = "rssAddr")
    private String rssAddr; //路段位置
    @JSONField(name = "lng")
    private double lng; //节点经度
    @JSONField(name = "lat")
    private double lat; //节点纬度

    public DmRoadSectionDTO(long rssId, String rssName, String rssAddr, double lng, double lat) {
        this.rssId = rssId;
        this.rssName = rssName;
        this.rssAddr = rssAddr;
        this.lng = lng;
        this.lat = lat;
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
