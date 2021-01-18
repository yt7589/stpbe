package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class KsRssDTO extends BaseDTO {
    @JSONField(name = "rssId")
    private long rssId;
    @JSONField(name = "rssName")
    private String rssName;
    @JSONField(name = "level")
    private int level;
    @JSONField(name = "groupCode")
    private String groupCode;

    public KsRssDTO() {
        super();
    }

    public KsRssDTO(long rssId, String rssName, int level, String groupCode) {
        this.rssId = rssId;
        this.rssName = rssName;
        this.level = level;
        this.groupCode = groupCode;
    }

    public JSONObject toJsonObject(){
        JSONObject obj = new JSONObject();
        obj.put("rssId", this.rssId);
        obj.put("rssName", this.rssName);
        obj.put("level", this.level);
        obj.put("groupCode", this.groupCode);
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
}
