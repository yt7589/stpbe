package com.zhuanjingkj.zjcbe.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.util.Date;

@TableName("t_video_stream")
public class VideoStreamEntity {
    @TableId("video_stream_id")
    private Long videoStreamId;
    @TableField("video_stream_name")
    private String videoStreamName;
    @TableField("rtsp_url")
    private String rtspUrl;
    @TableField("rtmp_url")
    private String rtmpUrl;
    @TableField("state")
    private int state;
    @TableField(value = "start_time")
    private Date startTime;
    @TableField(value = "end_time")
    private Date endTime;

    public Long getVideoStreamId() {
        return videoStreamId;
    }

    public void setVideoStreamId(Long videoStreamId) {
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
