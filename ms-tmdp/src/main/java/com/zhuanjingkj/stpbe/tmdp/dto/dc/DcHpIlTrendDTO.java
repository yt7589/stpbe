package com.zhuanjingkj.stpbe.tmdp.dto.dc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 数据中心 =》 全部数据
 * 违章量走势
 */
public class DcHpIlTrendDTO extends BaseDTO {
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "count")
    private Integer count;

    public DcHpIlTrendDTO(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
