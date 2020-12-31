package com.zhuanjingkj.stpbe.tmdp.rto.dm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DeleteRoadSectionFromRsRTO extends BaseDTO {
    @JSONField(name = "rssId")
    private long rssId;

    public long getRssId() {
        return rssId;
    }

    public void setRssId(long rssId) {
        this.rssId = rssId;
    }
}
