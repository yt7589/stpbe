package com.zhuanjingkj.stpbe.data.rto.dm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class AddAreaToSpaceRTO extends BaseRTO {
    @JSONField(name = "areaId")
    private long areaId;
    @JSONField(name = "parentId")
    private Integer parentId;
    @JSONField(name = "areaName")
    private String areaName;

    public AddAreaToSpaceRTO() {
        super();
    }

    public AddAreaToSpaceRTO(long areaId, Integer parentId, String areaName) {
        this.areaId = areaId;
        this.parentId = parentId;
        this.areaName = areaName;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
