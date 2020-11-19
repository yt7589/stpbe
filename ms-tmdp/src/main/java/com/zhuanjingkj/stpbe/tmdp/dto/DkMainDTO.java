package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DkMainDTO extends BaseDTO {
    @JSONField(name = "dkTitf")
    private DkTitfDTO dkTitf;

    public DkTitfDTO getDkTitf() {
        return dkTitf;
    }

    public void setDkTitf(DkTitfDTO dkTitf) {
        this.dkTitf = dkTitf;
    }
}
