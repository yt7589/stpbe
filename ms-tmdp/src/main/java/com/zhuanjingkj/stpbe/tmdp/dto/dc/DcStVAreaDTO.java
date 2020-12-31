package com.zhuanjingkj.stpbe.tmdp.dto.dc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 数据中心 =》 数据统计
 * 地区前5
 */
public class DcStVAreaDTO extends BaseDTO {
    @JSONField(name = "areaId")
    private long areaId;
    @JSONField(name = "areaName")
    private String areaName;
    @JSONField(name = "count")
    private Integer count;

    public DcStVAreaDTO(long areaId, String areaName, Integer count) {
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
