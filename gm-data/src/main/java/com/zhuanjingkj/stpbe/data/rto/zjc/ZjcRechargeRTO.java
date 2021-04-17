package com.zhuanjingkj.stpbe.data.rto.zjc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class ZjcRechargeRTO extends BaseRTO {
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
}
