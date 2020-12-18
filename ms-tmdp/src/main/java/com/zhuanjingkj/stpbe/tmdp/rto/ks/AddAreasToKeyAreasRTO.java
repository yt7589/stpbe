package com.zhuanjingkj.stpbe.tmdp.rto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

import java.util.List;

public class AddAreasToKeyAreasRTO extends BaseRTO {
    @JSONField(name = "areas")
    private List<Integer> areas;

    public List<Integer> getAreas() {
        return areas;
    }

    public void setAreas(List<Integer> areas) {
        this.areas = areas;
    }
}
