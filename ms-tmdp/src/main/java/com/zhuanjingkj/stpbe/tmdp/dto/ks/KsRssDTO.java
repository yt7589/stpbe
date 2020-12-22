package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import org.json.JSONObject;

public class KsRssDTO extends BaseDTO {
    @JSONField(name = "rssId")
    private long rssId;
    @JSONField(name = "rssName")
    private String rssName;
    @JSONField(name = "parentId")
    private long parentId;
    @JSONField(name = "level")
    private int level;
    @JSONField(name = "groupCode")
    private String groupCode;

    public KsRssDTO(long rssId, String rssName, long parentId, int level, String groupCode) {
        this.rssId = rssId;
        this.rssName = rssName;
        this.parentId = parentId;
        this.level = level;
        this.groupCode = groupCode;
    }

    public JSONObject toJsonObject(){
        JSONObject obj = new JSONObject();
        obj.put("rssId", this.rssId);
        obj.put("rssName", this.rssName);
        obj.put("parentId", this.parentId);
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

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
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
