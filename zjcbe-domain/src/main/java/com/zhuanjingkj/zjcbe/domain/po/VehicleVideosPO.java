package com.zhuanjingkj.zjcbe.domain.po;

import java.util.Date;

public class VehicleVideosPO {

  private String videoId;
  private String videoName;
  private String videoUrl;
  private String videoPath;
  private String videoType;
  private double videoSize;
  private int streamId;
  private String rtspUrl;
  private String rtmpUrl;
  private int createBy;
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
