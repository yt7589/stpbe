package com.zhuanjingkj.stpbe.tmdp.dto.camera;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on 2020.11.25
 **/
public class CameraDTO extends BaseDTO {
    private Long cameraId;
    private String cameraCode;
    private Long siteId;
    private String startTime;
    private String endTime;
    private Integer recState;
    private Integer cameraTypeId;
    private String cameraTypeName;
    private String rtspUrl;
    private String rtpPushAddr;
    private String webUrl;
    private String ftpUrl;
    private String uploadUrl;
    private Integer status;
    private Integer regionId;
    private String regionName;
    private String city;
    private String direction;
    private Integer vehicleDirection;
    private String siteName;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getVehicleDirection() {
        return vehicleDirection;
    }

    public void setVehicleDirection(Integer vehicleDirection) {
        this.vehicleDirection = vehicleDirection;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Long getCameraId() {
        return cameraId;
    }

    public void setCameraId(Long cameraId) {
        this.cameraId = cameraId;
    }

    public String getCameraCode() {
        return cameraCode;
    }

    public void setCameraCode(String cameraCode) {
        this.cameraCode = cameraCode;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getRecState() {
        return recState;
    }

    public void setRecState(Integer recState) {
        this.recState = recState;
    }

    public Integer getCameraTypeId() {
        return cameraTypeId;
    }

    public void setCameraTypeId(Integer cameraTypeId) {
        this.cameraTypeId = cameraTypeId;
    }

    public String getCameraTypeName() {
        return cameraTypeName;
    }

    public void setCameraTypeName(String cameraTypeName) {
        this.cameraTypeName = cameraTypeName;
    }

    public String getRtspUrl() {
        return rtspUrl;
    }

    public void setRtspUrl(String rtspUrl) {
        this.rtspUrl = rtspUrl;
    }

    public String getRtpPushAddr() {
        return rtpPushAddr;
    }

    public void setRtpPushAddr(String rtpPushAddr) {
        this.rtpPushAddr = rtpPushAddr;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getFtpUrl() {
        return ftpUrl;
    }

    public void setFtpUrl(String ftpUrl) {
        this.ftpUrl = ftpUrl;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
