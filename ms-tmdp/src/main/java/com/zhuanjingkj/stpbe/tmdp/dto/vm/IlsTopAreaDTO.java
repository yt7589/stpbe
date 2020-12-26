package com.zhuanjingkj.stpbe.tmdp.dto.vm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class IlsTopAreaDTO extends BaseDTO {
    @JSONField(name = "areaId")
    private long areaId;
    @JSONField(name = "areaName")
    private String areaName;
    @JSONField(name = "count")
    private Integer count;

    public IlsTopAreaDTO(long areaId, String areaName, Integer count) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.count = count;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
