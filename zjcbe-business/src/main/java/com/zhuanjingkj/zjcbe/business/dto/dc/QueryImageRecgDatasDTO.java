package com.zhuanjingkj.zjcbe.business.dto.dc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class QueryImageRecgDatasDTO {
    @ApiModelProperty("总记录数")
    private int totalNum;
    @ApiModelProperty("应返回数量")
    private int dueNum;
    @ApiModelProperty("实际返回数量")
    private int realNum;
    @ApiModelProperty("数据列表")
    @JsonProperty("items")
    private List<QueryImageRecgDatasItemDTO> items;

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getDueNum() {
        return dueNum;
    }

    public void setDueNum(int dueNum) {
        this.dueNum = dueNum;
    }

    public int getRealNum() {
        return realNum;
    }

    public void setRealNum(int realNum) {
        this.realNum = realNum;
    }

    public List<QueryImageRecgDatasItemDTO> getItems() {
        return items;
    }

    public void setItems(List<QueryImageRecgDatasItemDTO> items) {
        this.items = items;
    }
}
