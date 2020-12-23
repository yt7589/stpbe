package com.zhuanjingkj.stpbe.tmdp.rto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

import java.util.List;

public class AddRsToRssRTO extends BaseRTO {
    @JSONField(name = "rssIds")
    private List<Integer> rssIds;

    public List<Integer> getRssIds() {
        return rssIds;
    }

    public void setRssIds(List<Integer> rssIds) {
        this.rssIds = rssIds;
    }
}
