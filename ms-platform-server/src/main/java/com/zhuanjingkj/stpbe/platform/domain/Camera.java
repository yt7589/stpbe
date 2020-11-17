package com.zhuanjingkj.stpbe.platform.domain;

import java.util.Date;

public class Camera {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_camera.camera_id
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    private Long cameraId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_camera.camera_code
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    private String cameraCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_camera.site_id
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    private Long siteId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_camera.start_time
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    private Date startTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_camera.end_time
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    private Date endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_camera.rec_state
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    private Byte recState;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_camera.camera_type_id
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    private Integer cameraTypeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_camera.rtsp_url
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    private String rtspUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_camera.rtp_push_addr
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    private String rtpPushAddr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_camera.web_url
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    private String webUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_camera.ftp_url
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    private String ftpUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_camera.upload_url
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    private String uploadUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_camera.camera_id
     *
     * @return the value of t_camera.camera_id
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public Long getCameraId() {
        return cameraId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_camera.camera_id
     *
     * @param cameraId the value for t_camera.camera_id
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public void setCameraId(Long cameraId) {
        this.cameraId = cameraId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_camera.camera_code
     *
     * @return the value of t_camera.camera_code
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public String getCameraCode() {
        return cameraCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_camera.camera_code
     *
     * @param cameraCode the value for t_camera.camera_code
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public void setCameraCode(String cameraCode) {
        this.cameraCode = cameraCode == null ? null : cameraCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_camera.site_id
     *
     * @return the value of t_camera.site_id
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public Long getSiteId() {
        return siteId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_camera.site_id
     *
     * @param siteId the value for t_camera.site_id
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_camera.start_time
     *
     * @return the value of t_camera.start_time
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_camera.start_time
     *
     * @param startTime the value for t_camera.start_time
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_camera.end_time
     *
     * @return the value of t_camera.end_time
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_camera.end_time
     *
     * @param endTime the value for t_camera.end_time
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_camera.rec_state
     *
     * @return the value of t_camera.rec_state
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public Byte getRecState() {
        return recState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_camera.rec_state
     *
     * @param recState the value for t_camera.rec_state
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public void setRecState(Byte recState) {
        this.recState = recState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_camera.camera_type_id
     *
     * @return the value of t_camera.camera_type_id
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public Integer getCameraTypeId() {
        return cameraTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_camera.camera_type_id
     *
     * @param cameraTypeId the value for t_camera.camera_type_id
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public void setCameraTypeId(Integer cameraTypeId) {
        this.cameraTypeId = cameraTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_camera.rtsp_url
     *
     * @return the value of t_camera.rtsp_url
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public String getRtspUrl() {
        return rtspUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_camera.rtsp_url
     *
     * @param rtspUrl the value for t_camera.rtsp_url
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public void setRtspUrl(String rtspUrl) {
        this.rtspUrl = rtspUrl == null ? null : rtspUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_camera.rtp_push_addr
     *
     * @return the value of t_camera.rtp_push_addr
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public String getRtpPushAddr() {
        return rtpPushAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_camera.rtp_push_addr
     *
     * @param rtpPushAddr the value for t_camera.rtp_push_addr
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public void setRtpPushAddr(String rtpPushAddr) {
        this.rtpPushAddr = rtpPushAddr == null ? null : rtpPushAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_camera.web_url
     *
     * @return the value of t_camera.web_url
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_camera.web_url
     *
     * @param webUrl the value for t_camera.web_url
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl == null ? null : webUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_camera.ftp_url
     *
     * @return the value of t_camera.ftp_url
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public String getFtpUrl() {
        return ftpUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_camera.ftp_url
     *
     * @param ftpUrl the value for t_camera.ftp_url
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public void setFtpUrl(String ftpUrl) {
        this.ftpUrl = ftpUrl == null ? null : ftpUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_camera.upload_url
     *
     * @return the value of t_camera.upload_url
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public String getUploadUrl() {
        return uploadUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_camera.upload_url
     *
     * @param uploadUrl the value for t_camera.upload_url
     *
     * @mbg.generated Tue Nov 17 19:02:07 CST 2020
     */
    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl == null ? null : uploadUrl.trim();
    }
}