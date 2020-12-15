package com.zhuanjingkj.stpbe.tmdp.dto;

import com.zhuanjingkj.stpbe.data.entity.Region;

/**
 * author by guoqiang
 * date on 2020.12.15
 **/
public class RegionBaseDTO extends Region {

    private String  parentRegionName;
    private Integer parentLevel;

    public String getParentRegionName() {
        return parentRegionName;
    }

    public void setParentRegionName(String parentRegionName) {
        this.parentRegionName = parentRegionName;
    }

    public Integer getParentLevel() {
        return parentLevel;
    }

    public void setParentLevel(Integer parentLevel) {
        this.parentLevel = parentLevel;
    }
}
