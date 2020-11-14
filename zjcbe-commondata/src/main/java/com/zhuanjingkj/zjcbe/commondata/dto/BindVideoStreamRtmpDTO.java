package com.zhuanjingkj.zjcbe.commondata.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class BindVideoStreamRtmpDTO {
    @JSONField(name="state")
    private int state;
    @JSONField(name="msg")
    private String msg;
    @JSONField(name="rtmpUrl")
    private String rtmpUrl;

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

    public String getRtmpUrl() {
        return rtmpUrl;
    }

    public void setRtmpUrl(String rtmpUrl) {
        this.rtmpUrl = rtmpUrl;
    }
}
