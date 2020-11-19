package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.Map;

public class DkVtpDTO extends BaseDTO {
    @JSONField(name = "percents")
    private Map<String, Integer> percents;

    public Map<String, Integer> getPercents() {
        return percents;
    }

    public void setPercents(Map<String, Integer> percents) {
        this.percents = percents;
    }
}
