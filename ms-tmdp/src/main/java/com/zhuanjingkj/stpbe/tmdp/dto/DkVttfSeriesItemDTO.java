package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DkVttfSeriesItemDTO extends BaseDTO {
    @JSONField(name = "timeInterval")
    private String timeInterval;
    @JSONField(name = "count")
    private int count;

    public DkVttfSeriesItemDTO(String timeInterval, int count) {
        this.timeInterval = timeInterval;
        this.count = count;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
