package com.zhuanjingkj.stpbe.data.rto.zjc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class DeleteChargesRTO extends BaseRTO {
    @JSONField(name = "charge_id")
    private long charge_id;

    public long getCharge_id() {
        return charge_id;
    }

    public void setCharge_id(long charge_id) {
        this.charge_id = charge_id;
    }
}
