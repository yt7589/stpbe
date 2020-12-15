package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 首页=》数据看板：左侧第一行第一个，本地外地车辆占比
 */
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
