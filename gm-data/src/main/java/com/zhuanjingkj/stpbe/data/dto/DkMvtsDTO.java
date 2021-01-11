package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class DkMvtsDTO extends BaseDTO {
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "count")
    private int count;

    public DkMvtsDTO() {
        super();
    }
    public DkMvtsDTO(String name, int count) {
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
