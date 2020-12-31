package com.zhuanjingkj.stpbe.tmdp.dto.dc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 数据报告
 * 分区高峰时段拥堵排名
 */
public class DcRtAreaJamDTO extends BaseDTO {
    @JSONField(name = "areaId")
    private long areaId; //地区id
    @JSONField(name = "areaName")
    private String areaName; //地区名称
    @JSONField(name = "lng")
    private double lng; //经度
    @JSONField(name = "lat")
    private double lat; //纬度
    @JSONField(name = "count")
    private double count; //拥堵指数
    @JSONField(name = "rg")
    private double rg; //环比

    public DcRtAreaJamDTO(long areaId, String areaName, double lng, double lat, double count, double rg) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.lng = lng;
        this.lat = lat;
        this.count = count;
        this.rg = rg;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
