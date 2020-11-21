package com.zhuanjingkj.stpbe.tebs.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class PostTvisJsonDTO extends BaseDTO {
    @JSONField(name = "tvisId")
    private long tvisId;

    public long getTvisId() {
        return tvisId;
    }

    public void setTvisId(long tvisId) {
        this.tvisId = tvisId;
    }
}
