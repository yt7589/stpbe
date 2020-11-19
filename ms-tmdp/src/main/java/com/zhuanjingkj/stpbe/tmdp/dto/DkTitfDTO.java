package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class DkTitfDTO extends BaseDTO {
    @JSONField(name = "todayTraffics")
    private List<DkTitfItemDTO> todayTraffics;
    @JSONField(name = "yesterdayTraffics")
    private List<DkTitfItemDTO> yesterdayTraffics;

    public List<DkTitfItemDTO> getTodayTraffics() {
        return todayTraffics;
    }

    public void setTodayTraffics(List<DkTitfItemDTO> todayTraffics) {
        this.todayTraffics = todayTraffics;
    }

    public List<DkTitfItemDTO> getYesterdayTraffics() {
        return yesterdayTraffics;
    }

    public void setYesterdayTraffics(List<DkTitfItemDTO> yesterdayTraffics) {
        this.yesterdayTraffics = yesterdayTraffics;
    }
}
