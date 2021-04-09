package com.zhuanjingkj.stpbe.data.vo;

import com.alibaba.fastjson.annotation.JSONField;

public class ZjcVideoVO {
    @JSONField(name ="videoName")
    private String videoName;
    @JSONField(name ="streamId")
    private String streamId;
    @JSONField(name ="rtspUrl")
    private String rtspUrl;
    @JSONField(name ="rtmpUrl")
    private String rtmpUrl;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
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
