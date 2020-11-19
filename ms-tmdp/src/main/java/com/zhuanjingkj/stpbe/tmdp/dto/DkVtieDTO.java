package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DkVtieDTO extends BaseDTO {
    @JSONField(name = "internalPercent")
    private int internalPercent;
    @JSONField(name = "externalPercent")
    private int externalPercent;

    public int getInternalPercent() {
        return internalPercent;
    }

    public void setInternalPercent(int internalPercent) {
        this.internalPercent = internalPercent;
    }

    public int getExternalPercent() {
        return externalPercent;
    }

    public void setExternalPercent(int externalPercent) {
        this.externalPercent = externalPercent;
    }
}
