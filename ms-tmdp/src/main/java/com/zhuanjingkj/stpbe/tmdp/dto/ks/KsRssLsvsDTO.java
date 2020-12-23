package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class KsRssLsvsDTO extends BaseDTO {
    @JSONField(name = "gcxh")
    private long gcxh; // 过车序号（原始图片）
    @JSONField(name = "sxh")
    private long sxh; // 全局顺序号（小图序号）
    @JSONField(name = "rssId")
    private long rssId;
    @JSONField(name = "rssName")
    private String rssName;
    @JSONField(name = "cccurTime")
    private String occurTime;
    @JSONField(name = "hphm")
    private String hphm;
    @JSONField(name = "totalTimes")
    private int totalTimes;

    public KsRssLsvsDTO(long gcxh, long sxh, long rssId, String rssName,
                      String occurTime, String hphm, int totalTimes) {
        this.gcxh = gcxh;
        this.sxh = sxh;
        this.rssId = rssId;
        this.rssName = rssName;
        this.occurTime = occurTime;
        this.hphm = hphm;
        this.totalTimes = totalTimes;
    }

    public com.alibaba.fastjson.JSONObject toJsonObject() {
        com.alibaba.fastjson.JSONObject obj = new JSONObject();
        obj.put("gcxh", gcxh);
        obj.put("sxh", sxh);
        obj.put("rssId", rssId);
        obj.put("rssName", rssName);
        obj.put("occurTime", occurTime);
        obj.put("hphm", hphm);
        obj.put("totalTimes", totalTimes);
        return obj;
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
