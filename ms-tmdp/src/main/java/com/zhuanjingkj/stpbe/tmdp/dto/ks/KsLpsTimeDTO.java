package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class KsLpsTimeDTO extends BaseDTO {
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "count")
    private int count;

    public KsLpsTimeDTO(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
