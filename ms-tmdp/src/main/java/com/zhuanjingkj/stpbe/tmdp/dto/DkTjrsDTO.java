package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class DkTjrsDTO extends BaseDTO {
    @JSONField(name = "items")
    private List<DkTjrsItemDTO> items;

    public List<DkTjrsItemDTO> getItems() {
        return items;
    }

    public void setItems(List<DkTjrsItemDTO> items) {
        this.items = items;
    }
}
