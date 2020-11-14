package com.zhuanjingkj.zjcbe.commondata.rto;

public class CreateVideoStreamRTO extends BaseRTO {
    private long videoStreamId;
    private String videoStreamName;
    private String rtspUrl;
    private String rtmpUrl;

    public long getVideoStreamId() {
        return videoStreamId;
    }

    public void setVideoStreamId(long videoStreamId) {
        this.videoStreamId = videoStreamId;
    }

    public String getVideoStreamName() {
        return videoStreamName;
    }

    public void setVideoStreamName(String videoStreamName) {
        this.videoStreamName = videoStreamName;
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
