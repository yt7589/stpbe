package com.zhuanjingkj.zjcbe.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

@TableName("vehicle_analysis")
public class VehicleAnalysisEntity {

    @TableId(value = "analysisId")
    private String analysisId;
    @TableField("imageId")
    private String imageId;
    @TableField("createTime")
    private Date createTime;
    @TableField("gcxh")
    private String gcxh;
    @TableField("sxh")
    private int sxh;

    public String getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(String analysisId) {
        this.analysisId = analysisId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGcxh() {
        return gcxh;
    }

    public void setGcxh(String gcxh) {
        this.gcxh = gcxh;
    }

    public int getSxh() {
        return sxh;
    }

    public void setSxh(int sxh) {
        this.sxh = sxh;
    }
}
