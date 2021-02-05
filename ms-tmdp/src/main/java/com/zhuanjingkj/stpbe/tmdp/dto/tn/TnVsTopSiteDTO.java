package com.zhuanjingkj.stpbe.tmdp.dto.tn;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class TnVsTopSiteDTO extends BaseDTO {
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "lng")
    private Double lng;
    @JSONField(name = "lat")
    private Double lat;
    @JSONField(name ="count")
    private Integer count;

    public TnVsTopSiteDTO(String name, Double lng, Double lat, Integer count) {
        this.name = name;
        this.lng = lng;
        this.lat = lat;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
