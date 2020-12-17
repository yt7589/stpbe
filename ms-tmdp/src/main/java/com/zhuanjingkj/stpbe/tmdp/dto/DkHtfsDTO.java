package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DkHtfsDTO extends BaseDTO {
    @JSONField(name = "todayTf")
    private long todayTf;
    @JSONField(name = "weekTf")
    private long weekTf;
    @JSONField(name = "monthTf")
    private long monthTf;
    @JSONField(name = "dailyTf")
    private long dailyTf;

    public long getTodayTf() {
        return todayTf;
    }

    public void setTodayTf(long todayTf) {
        this.todayTf = todayTf;
    }

    public long getWeekTf() {
        return weekTf;
    }

    public void setWeekTf(long weekTf) {
        this.weekTf = weekTf;
    }

    public long getMonthTf() {
        return monthTf;
    }

    public void setMonthTf(long monthTf) {
        this.monthTf = monthTf;
    }

    public long getDailyTf() {
        return dailyTf;
    }

    public void setDailyTf(long dailyTf) {
        this.dailyTf = dailyTf;
    }
}
