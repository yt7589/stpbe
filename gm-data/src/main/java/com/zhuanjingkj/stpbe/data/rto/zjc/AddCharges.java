package com.zhuanjingkj.stpbe.data.rto.zjc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

/**
 * 收费规则
 */
public class AddCharges extends BaseRTO {
    @JSONField(name = "charge_id")
    private Integer  charge_id; //id
    @JSONField(name = "charge_name")
    private String charge_name; //名称
    @JSONField(name = "free_time")
    private Integer free_time; //免费时长
    @JSONField(name = "pic_num")
    private Integer pic_num; //每天检测图片数量
    @JSONField(name = "pic_per_unit")
    private Integer pic_per_unit; //每张图片的检测费用
    @JSONField(name = "video_time")
    private Integer video_time; //每天可播放视频时长
    @JSONField(name = "video_per_unit")
    private Integer video_per_unit; //每秒视频的播放费用
    @JSONField(name = "remark")
    private String remark; //备注

    public Integer getCharge_id() {
        return charge_id;
    }

    public void setCharge_id(Integer charge_id) {
        this.charge_id = charge_id;
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

    public Integer getVideo_per_unit() {
        return video_per_unit;
    }

    public void setVideo_per_unit(Integer video_per_unit) {
        this.video_per_unit = video_per_unit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
