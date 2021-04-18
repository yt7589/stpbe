package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class ZjcConsumeRecordDTO extends BaseDTO{
    @JSONField(name = "crId")
    private long crId;
    @JSONField(name = "userId")
    private long userId;
    @JSONField(name = "amount")
    private Double amount;
    @JSONField(name = "desc")
    private String desc;
    @JSONField(name = "createTime")
    private String createTime;

    public long getCrId() {
        return crId;
    }

    public void setCrId(long crId) {
        this.crId = crId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
