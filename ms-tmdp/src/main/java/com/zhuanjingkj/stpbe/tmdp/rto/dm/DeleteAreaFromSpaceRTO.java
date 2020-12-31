package com.zhuanjingkj.stpbe.tmdp.rto.dm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class DeleteAreaFromSpaceRTO extends BaseRTO {
    @JSONField(name = "areaId")
    private long areaId;

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }
}
