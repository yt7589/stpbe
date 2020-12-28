package com.zhuanjingkj.stpbe.tmdp.dto.vm;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 车辆违章走势（年）
 */
public class VmIlsVsTrend extends BaseDTO {
    @JSONField(name = "year")
    private String year;
    @JSONField(name = "count")
    private Integer count;

    public VmIlsVsTrend(String year, Integer count) {
        this.year = "Y_" + year;
        this.count = count;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
