package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class AreaDTO extends BaseDTO {
    @JSONField(name = "areaId")
    private long areaId;
    @JSONField(name = "areaName")
    private String areaName;
    @JSONField(name = "parentId")
    private long parentId;
    @JSONField(name = "level")
    private int level;
    @JSONField(name = "groupCode")
    private String groupCode;

    public AreaDTO(long areaId, String areaName, long parentId, int level, String groupCode) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.parentId = parentId;
        this.level = level;
        this.groupCode = groupCode;
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
