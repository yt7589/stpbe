package com.zhuanjingkj.stpbe.tmdp.rto.tn;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class TnDeviceRTO extends BaseDTO {
    @JSONField(name = "diId")
    private String diId;

    public String getSiteId() {
        return diId;
    }

    public void setSiteId(String siteId) {
        this.diId = siteId;
    }
}
