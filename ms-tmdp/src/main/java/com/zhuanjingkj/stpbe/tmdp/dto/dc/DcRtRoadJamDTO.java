package com.zhuanjingkj.stpbe.tmdp.dto.dc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 数据报告
 * 高峰时段拥堵路名排名
 */
public class DcRtRoadJamDTO extends BaseDTO {
    @JSONField(name = "rdId")
    private long rdId; //路段id
    @JSONField(name = "rdName")
    private String rdName; //路段名称
    @JSONField(name = "lng")
    private double lng; //经度
    @JSONField(name = "lat")
    private double lat; //纬度
    @JSONField(name = "count")
    private double count; //拥堵指数
    @JSONField(name = "rg")
    private double rg; //环比

    public DcRtRoadJamDTO(long rdId, String rdName, double lng, double lat, double count, double rg) {
        this.rdId = rdId;
        this.rdName = rdName;
        this.lng = lng;
        this.lat = lat;
        this.count = count;
        this.rg = rg;
    }

    public long getRdId() {
        return rdId;
    }

    public void setRdId(long rdId) {
        this.rdId = rdId;
    }

    public String getRdName() {
        return rdName;
    }

    public void setRdName(String rdName) {
        this.rdName = rdName;
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

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getRg() {
        return rg;
    }

    public void setRg(double rg) {
        this.rg = rg;
    }
}
