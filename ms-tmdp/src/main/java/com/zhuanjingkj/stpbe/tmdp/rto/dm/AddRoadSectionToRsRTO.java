package com.zhuanjingkj.stpbe.tmdp.rto.dm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class AddRoadSectionToRsRTO extends BaseRTO {
    @JSONField(name = "rssName")
    private String rssName;

    public String getRssName() {
        return rssName;
    }

    public void setRssName(String rssName) {
        this.rssName = rssName;
    }
}
