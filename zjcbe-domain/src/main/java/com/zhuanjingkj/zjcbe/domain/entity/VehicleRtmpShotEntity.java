package com.zhuanjingkj.zjcbe.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

@TableName("vehicle_rtmp_shot")
public class VehicleRtmpShotEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField("sxh")
    private int sxh;
    @TableField("streamId")
    private int streamId;
    @TableField("timeStamp")
    private int timeStamp;
    @TableField("imageUrl")
    private String imageUrl;
    @TableField("imagePath")
    private String imagePath;
    @TableField("imageType")
    private String imageType;
    @TableField("imageSize")
    private double imageSize;
    @TableField("createtime")
    private Date createtime;

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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
