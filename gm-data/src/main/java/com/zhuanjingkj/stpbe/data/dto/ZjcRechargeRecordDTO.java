package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class ZjcRechargeRecordDTO extends BaseDTO {
    @JSONField(name = "rdId")
    private Integer rdId;
    @JSONField(name = "orderNo")
    private String orderNo;
    @JSONField(name = "customerId")
    private Integer customerId;
    @JSONField(name = "amount")
    private Double amount;
    @JSONField(name = "operator")
    private String operator;
    @JSONField(name = "corporateName")
    private String corporateName;
    @JSONField(name = "balance")
    private Double balance;
    @JSONField(name = "createTime")
    private String createTime;

    public Integer getRdId() {
        return rdId;
    }

    public void setRdId(Integer rdId) {
        this.rdId = rdId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
