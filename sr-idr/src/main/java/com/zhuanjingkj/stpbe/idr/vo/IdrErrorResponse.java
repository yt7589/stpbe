package com.zhuanjingkj.stpbe.idr.vo;

public class IdrErrorResponse {
    private int code;
    private String gcxh;
    private String msg;

    public IdrErrorResponse(int code, String gcxh, String msg) {
        this.code = code;
        this.gcxh = gcxh;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getGcxh() {
        return gcxh;
    }

    public void setGcxh(String gcxh) {
        this.gcxh = gcxh;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
