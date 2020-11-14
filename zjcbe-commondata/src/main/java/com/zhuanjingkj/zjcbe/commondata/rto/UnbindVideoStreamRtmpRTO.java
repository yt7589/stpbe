package com.zhuanjingkj.zjcbe.commondata.rto;

import com.alibaba.fastjson.annotation.JSONField;

public class UnbindVideoStreamRtmpRTO {
    @JSONField(name="state")
    private int state;
    @JSONField(name="msg")
    private String msg;
    @JSONField(name="videoStreamId")
    private long videoStreamId;
    @JSONField(name="rtspUrl")
    private String rtspUrl;
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

    public long getVideoStreamId() {
        return videoStreamId;
    }

    public void setVideoStreamId(long videoStreamId) {
        this.videoStreamId = videoStreamId;
    }

    public String getRtspUrl() {
        return rtspUrl;
    }

    public void setRtspUrl(String rtspUrl) {
        this.rtspUrl = rtspUrl;
    }

    public String getRtmpUrl() {
        return rtmpUrl;
    }

    public void setRtmpUrl(String rtmpUrl) {
        this.rtmpUrl = rtmpUrl;
    }
}
