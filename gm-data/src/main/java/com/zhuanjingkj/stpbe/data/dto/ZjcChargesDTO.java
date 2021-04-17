package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class ZjcChargesDTO extends BaseDTO{
    @JSONField(name = "chargeId")
    private Integer  chargeId; //id
    @JSONField(name = "chargeName")
    private String charge_name; //名称
    @JSONField(name = "freeTime")
    private Integer free_time; //免费时长
    @JSONField(name = "picNum")
    private Integer pic_num; //每天检测图片数量
    @JSONField(name = "picPerUnit")
    private Integer pic_per_unit; //每张图片的检测费用
    @JSONField(name = "videoTime")
    private Integer video_time; //每天可播放视频时长
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

    public String getCharge_name() {
        return charge_name;
    }

    public void setCharge_name(String charge_name) {
        this.charge_name = charge_name;
    }

    public Integer getFree_time() {
        return free_time;
    }

    public void setFree_time(Integer free_time) {
        this.free_time = free_time;
    }

    public Integer getPic_num() {
        return pic_num;
    }

    public void setPic_num(Integer pic_num) {
        this.pic_num = pic_num;
    }

    public Integer getPic_per_unit() {
        return pic_per_unit;
    }

    public void setPic_per_unit(Integer pic_per_unit) {
        this.pic_per_unit = pic_per_unit;
    }

    public Integer getVideo_time() {
        return video_time;
    }

    public void setVideo_time(Integer video_time) {
        this.video_time = video_time;
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
