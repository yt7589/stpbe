package com.zhuanjingkj.stpbe.tmdp.rto.tn;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class TnVaDeviceRTO extends BaseDTO {
    @JSONField(name = "diId")
    private String diId;

    public String getDiId() {
        return diId;
    }

    public void setDiId(String diId) {
        this.diId = diId;
    }
}
