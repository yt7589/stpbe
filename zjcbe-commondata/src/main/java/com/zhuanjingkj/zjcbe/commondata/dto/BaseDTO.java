package com.zhuanjingkj.zjcbe.commondata.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class BaseDTO {
    @JSONField(name="state")
    protected int state;
    @JSONField(name="msg")
    protected String msg;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
