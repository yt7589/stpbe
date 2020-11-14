package com.zhuanjingkj.zjcbe.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

@TableName("vehicle_videos")
public class VehicleVideosEntity {

    @TableId(value = "videoId")
    private String videoId;
    @TableField("videoName")
    private String videoName;
    @TableField("videoUrl")
    private String videoUrl;
    @TableField("videoPath")
    private String videoPath;
    @TableField("videoType")
    private String videoType;
    @TableField("videoSize")
    private double videoSize;
    @TableField("streamId")
    private int streamId;
    @TableField("rtspUrl")
    private String rtspUrl;
    @TableField("rtmpUrl")
    private String rtmpUrl;
    @TableField("createBy")
    private int createBy;
    @TableField("createTime")
    private Date createTime;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public double getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(double videoSize) {
        this.videoSize = videoSize;
    }

    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
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

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
