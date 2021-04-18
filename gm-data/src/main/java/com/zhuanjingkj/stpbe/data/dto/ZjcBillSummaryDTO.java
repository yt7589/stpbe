package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class ZjcBillSummaryDTO extends BaseDTO{
    @JSONField(name = "totalRecharge")
    private Double totalRecharge; //总充值数
    @JSONField(name = "balance")
    private Double balance; //余额
    @JSONField(name = "picNum")
    private Integer picNum; //总识别图片数
    @JSONField(name = "duration")
    private Integer duration; //视频观看时长

    public ZjcBillSummaryDTO(Double totalRecharge, Double balance, Integer picNum, Integer duration) {
        this.totalRecharge = totalRecharge;
        this.balance = balance;
        this.picNum = picNum;
        this.duration = duration;
    }

    public Double getTotalRecharge() {
        return totalRecharge;
    }

    public void setTotalRecharge(Double totalRecharge) {
        this.totalRecharge = totalRecharge;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getPicNum() {
        return picNum;
    }

    public void setPicNum(Integer picNum) {
        this.picNum = picNum;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
