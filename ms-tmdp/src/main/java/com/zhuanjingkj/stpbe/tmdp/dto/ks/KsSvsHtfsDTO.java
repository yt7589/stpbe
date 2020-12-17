package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class KsSvsHtfsDTO extends BaseDTO {
    @JSONField(name = "todaySvNum")
    private long todaySvNum; // 本日重点车辆总数
    @JSONField(name = "todayDevNum")
    private long todayDevNum; // 本日在线设备总数
    @JSONField(name = "todayWarnNum")
    private long todayWarnNum; // 本日告警总数
    @JSONField(name = "todayKakvNum")
    private long todayKakvNum; // 本日重点区域重点车辆总数

    public long getTodaySvNum() {
        return todaySvNum;
    }

    public void setTodaySvNum(long todaySvNum) {
        this.todaySvNum = todaySvNum;
    }

    public long getTodayDevNum() {
        return todayDevNum;
    }

    public void setTodayDevNum(long todayDevNum) {
        this.todayDevNum = todayDevNum;
    }

    public long getTodayWarnNum() {
        return todayWarnNum;
    }

    public void setTodayWarnNum(long todayWarnNum) {
        this.todayWarnNum = todayWarnNum;
    }

    public long getTodayKakvNum() {
        return todayKakvNum;
    }

    public void setTodayKakvNum(long todayKakvNum) {
        this.todayKakvNum = todayKakvNum;
    }
}
