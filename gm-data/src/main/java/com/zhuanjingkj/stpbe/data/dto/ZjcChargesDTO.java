package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class ZjcChargesDTO extends BaseDTO{
    @JSONField(name = "chargeId")
    private Integer  chargeId; //id
    @JSONField(name = "chargeName")
    private String chargeName; //名称
    @JSONField(name = "freeTime")
    private Integer freeTime; //免费时长
    @JSONField(name = "picNum")
    private Integer picNum; //每天检测图片数量
    @JSONField(name = "picPerUnit")
    private Integer picPerUnit; //每张图片的检测费用
    @JSONField(name = "videoTime")
    private Integer videoTime; //每天可播放视频时长
    @JSONField(name = "videoPerUnit")
    private Integer videoPerUnit; //每秒视频的播放费用
    @JSONField(name = "remark")
    private String remark; //备注
    @JSONField(name = "rule")
    private String rule;

    public Integer getChargeId() {
        return chargeId;
    }

    public void setChargeId(Integer chargeId) {
        this.chargeId = chargeId;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public Integer getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(Integer freeTime) {
        this.freeTime = freeTime;
    }

    public Integer getPicNum() {
        return picNum;
    }

    public void setPicNum(Integer picNum) {
        this.picNum = picNum;
    }

    public Integer getPicPerUnit() {
        return picPerUnit;
    }

    public void setPicPerUnit(Integer picPerUnit) {
        this.picPerUnit = picPerUnit;
    }

    public Integer getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Integer videoTime) {
        this.videoTime = videoTime;
    }

    public Integer getVideoPerUnit() {
        return videoPerUnit;
    }

    public void setVideoPerUnit(Integer videoPerUnit) {
        this.videoPerUnit = videoPerUnit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
