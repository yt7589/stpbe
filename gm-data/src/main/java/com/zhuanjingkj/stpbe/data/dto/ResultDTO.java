package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class ResultDTO<T extends BaseDTO> {
    @JSONField(name="code")
    protected int code;
    @JSONField(name="msg")
    protected String msg;
    @JSONField(name="data")
    protected T data;

    public ResultDTO() {
        code = 0;
        msg = "";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BaseDTO getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
