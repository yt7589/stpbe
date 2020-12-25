package com.zhuanjingkj.stpbe.tmdp.rto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class DeleteVehicleFromVcRTO extends BaseRTO {
    @JSONField(name = "vcId")
    private long vcId;

    public long getVcId() {
        return vcId;
    }

    public void setVcId(long vcId) {
        this.vcId = vcId;
    }
}
