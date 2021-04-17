package com.zhuanjingkj.stpbe.data.rto.zjc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class DeleteChargesRTO extends BaseRTO {
    @JSONField(name = "chargeId")
    private long chargeId;

    public long getChargeId() {
        return chargeId;
    }

    public void setChargeId(long chargeId) {
        this.chargeId = chargeId;
    }
}
