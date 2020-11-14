package com.zhuanjingkj.zjcbe.commondata.po;

import java.util.Date;

public class VideoStreamPO {
    private long videoStreamId;
    private String videoStreamName;
    private String rtspUrl;
    private String rtmpUrl;
    private int state;
    private Date startTime;
    private Date endTime;

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
