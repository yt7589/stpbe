package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 数据库查询DTO
 */
public class DbQrsDTO extends BaseDTO{
    @JSONField(name = "total")
    private int total; // 总记录数
    @JSONField(name = "realNum")
    private int realNum; // 实际返回记录数
    @JSONField(name = "startIndex")
    private int startIndex; // 开始下标
    @JSONField(name = "amount")
    private int amount; // 计划取的记录数
    @JSONField(name = "recs")
    private List<? extends BaseDTO> recs;

    public DbQrsDTO(int total, int realNum, int startIndex, int amount, List<? extends BaseDTO> recs) {
        this.total = total;
        this.realNum = realNum;
        this.startIndex = startIndex;
        this.amount = amount;
        this.recs = recs;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRealNum() {
        return realNum;
    }

    public void setRealNum(int realNum) {
        this.realNum = realNum;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<? extends BaseDTO> getRecs() {
        return recs;
    }

    public void setRecs(List<? extends BaseDTO> recs) {
        this.recs = recs;
    }
}
