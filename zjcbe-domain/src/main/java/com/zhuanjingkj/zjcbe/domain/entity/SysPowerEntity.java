package com.zhuanjingkj.zjcbe.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

@TableName("sys_power")
public class SysPowerEntity {

    @TableId(value = "powerId", type = IdType.AUTO)
    private int powerId;
    @TableField("powerName")
    private String powerName;
    @TableField("powerPath")
    private String powerPath;
    @TableField("powerType")
    private int powerType;
    @TableField("icon")
    private String icon;
    @TableField("status")
    private int status;
    @TableField("parentId")
    private int parentId;
    @TableField("remark")
    private String remark;
    @TableField("createBy")
    private int createBy;
    @TableField("createTime")
    private Date createTime;
    @TableField("updateBy")
    private int updateBy;
    @TableField("updateTime")
    private Date updateTime;

    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int powerId) {
        this.powerId = powerId;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getPowerPath() {
        return powerPath;
    }

    public void setPowerPath(String powerPath) {
        this.powerPath = powerPath;
    }

    public int getPowerType() {
        return powerType;
    }

    public void setPowerType(int powerType) {
        this.powerType = powerType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
