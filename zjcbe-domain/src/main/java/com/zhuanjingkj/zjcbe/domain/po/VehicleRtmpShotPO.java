package com.zhuanjingkj.zjcbe.domain.po;

import java.util.Date;

public class VehicleRtmpShotPO {

  private int id;
  private int sxh;
  private int streamId;
  private int timeStamp;
  private String imageUrl;
  private String imagePath;
  private String imageType;
  private double imageSize;
  private String createtime;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getSxh() {
    return sxh;
  }

  public void setSxh(int sxh) {
    this.sxh = sxh;
  }

  public int getStreamId() {
    return streamId;
  }

  public void setStreamId(int streamId) {
    this.streamId = streamId;
  }

  public int getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(int timeStamp) {
    this.timeStamp = timeStamp;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public String getImageType() {
    return imageType;
  }

  public void setImageType(String imageType) {
    this.imageType = imageType;
  }

  public double getImageSize() {
    return imageSize;
  }

  public void setImageSize(double imageSize) {
    this.imageSize = imageSize;
  }

  public String getCreatetime() {
    return createtime;
  }

  public void setCreatetime(String createtime) {
    this.createtime = createtime;
  }
}
