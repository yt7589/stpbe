package com.zhuanjingkj.stpbe.data.rto.dm;

import com.alibaba.fastjson.annotation.JSONField;

public class UpdateSpaceAreaRTO {
    @JSONField(name = "areaId")
    private long areaId;
    @JSONField(name = "areaName")
    private String areaName;
    @JSONField(name = "parentName")
    private String parentName;

    public UpdateSpaceAreaRTO() {
        super();
    }

    public UpdateSpaceAreaRTO(long areaId, String areaName, String parentName) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.parentName = parentName;
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
