package com.zhuanjingkj.stpbe.ca_tvis.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class SaveQuestionImageDTO extends BaseDTO {
    @JSONField(name = "state")
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
