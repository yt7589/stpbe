package com.zhuanjingkj.stpbe.tmdp.dto.camera;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on 2020.11.30
 **/
public class ImageDTO extends BaseDTO {

    private Long imageId;
    private String imageUrl;
    private String imagePath;
    private String uploadTime;
    private Long cameraId;
    private Long userId;
    private String lng;
    private String lat;
    private Integer vehicleNum;
    private Long videoStreamId;
    private Long imageTypeId;
    private String  detail;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getImageTypeId() {
        return imageTypeId;
    }

    public void setImageTypeId(Long imageTypeId) {
        this.imageTypeId = imageTypeId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
