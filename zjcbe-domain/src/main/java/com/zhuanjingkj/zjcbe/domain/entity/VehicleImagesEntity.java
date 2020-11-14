package com.zhuanjingkj.zjcbe.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

@TableName("vehicle_images")
public class VehicleImagesEntity {

    @TableId(value = "imageId")
    private String imageId;
    @TableField("imageName")
    private String imageName;
    @TableField("imageUrl")
    private String imageUrl;
    @TableField("imagePath")
    private String imagePath;
    @TableField("imageType")
    private String imageType;
    @TableField("imageSize")
    private double imageSize;
    @TableField("width")
    private int width;
    @TableField("height")
    private int height;
    @TableField("uploadBy")
    private int uploadBy;
    @TableField("uploadTime")
    private Date uploadTime;
    @TableField("timeStamp")
    private long timeStamp;
    @TableField("streamId")
    private Integer streamId;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getUploadBy() {
        return uploadBy;
    }

    public void setUploadBy(int uploadBy) {
        this.uploadBy = uploadBy;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getStreamId() {
        return streamId;
    }

    public void setStreamId(Integer streamId) {
        this.streamId = streamId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
