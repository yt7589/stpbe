package com.zhuanjingkj.stpbe.tmdp.rto.dm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class AddAreaToSpaceRTO extends BaseRTO {
    @JSONField(name = "parentCode")
    private String parentCode;
    @JSONField(name = "areaName")
    private String areaName;

    public AddAreaToSpaceRTO(String parentCode, String areaName) {
        this.parentCode = parentCode;
        this.areaName = areaName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
