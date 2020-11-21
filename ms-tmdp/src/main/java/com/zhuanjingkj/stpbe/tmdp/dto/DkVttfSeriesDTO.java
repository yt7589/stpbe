package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class DkVttfSeriesDTO {
    @JSONField(name = "seriesName")
    private String seriesName;
    @JSONField(name = "x")
    private String[] x;
    @JSONField(name = "y")
    private String[] y;

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String[] getX() {
        return x;
    }

    public void setX(String[] x) {
        this.x = x;
    }

    public String[] getY() {
        return y;
    }

    public void setY(String[] y) {
        this.y = y;
    }
}
