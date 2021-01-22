package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import org.json.JSONException;
import org.json.JSONObject;

public class KsRssSfvsDTO extends BaseDTO {
    @JSONField(name = "siteId")
    private long siteId;
    @JSONField(name = "siteName")
    private String siteName;
    @JSONField(name = "hphm")
    private String hphm;
    @JSONField(name = "totalTimes")
    private int totalTimes;
    @JSONField(name = "lng")
    private double lng;
    @JSONField(name = "lat")
    private double lat;

    public KsRssSfvsDTO(long siteId, String siteName, String hphm, int totalTimes, double lng, double lat) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.hphm = hphm;
        this.totalTimes = totalTimes;
        this.lng = lng;
        this.lat = lat;
    }

    public JSONObject toJsonObject(){
        JSONObject obj = new JSONObject();
        try {
            obj.put("siteId", this.siteId);
            obj.put("siteName", this.siteName);
            obj.put("hphm", this.hphm);
            obj.put("totalTimes", this.totalTimes);
            obj.put("lng", this.lng);
            obj.put("lat", this.lat);
        } catch (JSONException ex) {
        }
        return obj;
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
