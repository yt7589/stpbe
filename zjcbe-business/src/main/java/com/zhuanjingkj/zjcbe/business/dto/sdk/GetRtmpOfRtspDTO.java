package com.zhuanjingkj.zjcbe.business.dto.sdk;

import io.swagger.annotations.ApiModelProperty;

public class GetRtmpOfRtspDTO {
    @ApiModelProperty("获取结果")
    private int state;
    @ApiModelProperty("错误原因")
    private String msg;
    @ApiModelProperty("RTMP链接")
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
