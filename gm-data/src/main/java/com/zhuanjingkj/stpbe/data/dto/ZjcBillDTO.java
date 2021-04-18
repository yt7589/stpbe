package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class ZjcBillDTO extends BaseDTO {
    @JSONField(name = "billId")
    private long billId;
    @JSONField(name = "fileType")
    private Integer fileType; //0:图片  1:视频
    @JSONField(name = "fileAddr")
    private String fileAddr; //文件保存地址
    @JSONField(name = "startTime")
    private String startTime; //视频开始时间
    @JSONField(name = "endTime")
    private String endTime; //视频结束数据
    @JSONField(name = "createTime")
    private String createTime; //检测时间
    @JSONField(name = "ipAddr")
    private String ipAddr; //IP地址
    @JSONField(name = "duration")
    private String duration; //时长（视频）
    @JSONField(name = "amount")
    private Double amount;

    public long getBillId() {
        return billId;
    }

    public void setBillId(long billId) {
        this.billId = billId;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getFileAddr() {
        return fileAddr;
    }

    public void setFileAddr(String fileAddr) {
        this.fileAddr = fileAddr;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
