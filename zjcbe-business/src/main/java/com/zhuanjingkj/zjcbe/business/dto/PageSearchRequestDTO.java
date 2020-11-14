package com.zhuanjingkj.zjcbe.business.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;

public class PageSearchRequestDTO {
    @ApiModelProperty("页码")
    @Min(value = 1, message = "页码必须大于0")
    private int pageIndex = 1;

    @ApiModelProperty("页大小")
    @Min(value = 1, message = "页大小必须大于0")
    private int pageSize = 10;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
