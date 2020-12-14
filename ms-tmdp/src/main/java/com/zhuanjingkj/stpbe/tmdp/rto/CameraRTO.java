package com.zhuanjingkj.stpbe.tmdp.rto;

import com.zhuanjingkj.stpbe.common.util.Insert;
import com.zhuanjingkj.stpbe.common.util.Update;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

import javax.validation.constraints.NotNull;

/**
 * author by guoqiang
 * date on 2020.11.25
 **/
public class CameraRTO extends BaseRTO {

    @NotNull(groups = {Update.class},message = "设备ID不能为空")
    private Integer  cameraId;

    @NotNull(groups = {Insert.class},message = "设备编号不能为空")
    private String cameraCode;

    @NotNull(groups = {Insert.class},message = "设备节点不能为空")
    private Integer siteId;
    private String startTime;
    private String endTime;
    private Integer recState;
    @NotNull(groups = {Insert.class},message = "设备类型不能为空")
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

    @NotNull(groups = {Insert.class},message = "城市名称不能为空")
    private String city;
    @NotNull(groups = {Insert.class},message = "设备方向不能为空")
    private String direction;

    @NotNull(groups = {Insert.class},message = "车辆方向不能为空")
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

    public Integer getCameraId() {
        return cameraId;
    }

    public void setCameraId(Integer cameraId) {
        this.cameraId = cameraId;
    }

    public String getCameraCode() {
        return cameraCode;
    }

    public void setCameraCode(String cameraCode) {
        this.cameraCode = cameraCode;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
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
