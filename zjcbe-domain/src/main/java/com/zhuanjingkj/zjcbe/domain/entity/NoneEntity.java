package com.zhuanjingkj.zjcbe.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_none")
public class NoneEntity {
    @TableId(value = "none_id")
    private Long noneId;
    @TableField("noneName")
    private String noneName;

    public Long getNoneId() {
        return noneId;
    }

    public void setNoneId(Long noneId) {
        this.noneId = noneId;
    }

    public String getNoneName() {
        return noneName;
    }

    public void setNoneName(String noneName) {
        this.noneName = noneName;
    }
}
