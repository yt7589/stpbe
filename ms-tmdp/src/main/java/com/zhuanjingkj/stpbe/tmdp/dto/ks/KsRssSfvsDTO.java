package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import org.json.JSONObject;

public class KsRssSfvsDTO extends BaseDTO {
    @JSONField(name = "rssId")
    private long rssId;
    @JSONField(name = "rssName")
    private String rssName;
    @JSONField(name = "lng")
    private double lng;
    @JSONField(name = "lat")
    private double lat;

    public KsRssSfvsDTO(long rssId, String rssName, double lng, double lat) {
        this.rssId = rssId;
        this.rssName = rssName;
        this.lng = lng;
        this.lat = lat;
    }

    public JSONObject toJsonObject(){
        JSONObject obj = new JSONObject();
        obj.put("rssId", this.rssId);
        obj.put("rssName", this.rssName);
        obj.put("lng", this.lng);
        obj.put("lat", this.lat);
        return obj;
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
