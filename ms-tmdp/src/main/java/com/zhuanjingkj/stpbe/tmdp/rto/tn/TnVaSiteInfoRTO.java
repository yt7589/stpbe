package com.zhuanjingkj.stpbe.tmdp.rto.tn;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class TnVaSiteInfoRTO extends BaseDTO {
    @JSONField(name = "siteId")
    private long siteId;

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }
}
