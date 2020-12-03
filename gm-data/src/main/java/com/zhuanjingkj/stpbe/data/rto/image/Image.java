package com.zhuanjingkj.stpbe.data.rto.image;

import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

/**
 * author by guoqiang
 * date on 2020.12.02
 **/
public class Image extends BaseRTO {
    private Long imageId;
    private String imageUrl;
    private String imagePath;
    private String uploadTime;
    private Long cameraId;
    private String lng;
    private String lat;
    private Integer vehicleNum;
    private Long videoStreamId;
    private Integer imageType;
    private String presentationTimeStamp;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
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

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getCameraId() {
        return cameraId;
    }

    public void setCameraId(Long cameraId) {
        this.cameraId = cameraId;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Integer getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(Integer vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public Long getVideoStreamId() {
        return videoStreamId;
    }

    public void setVideoStreamId(Long videoStreamId) {
        this.videoStreamId = videoStreamId;
    }

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }


    public String getPresentationTimeStamp() {
        return presentationTimeStamp;
    }

    public void setPresentationTimeStamp(String presentationTimeStamp) {
        this.presentationTimeStamp = presentationTimeStamp;
    }
}
