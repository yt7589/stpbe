package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class DkVttfSeriesDTO {
    @JSONField(name = "seriesName")
    private String seriesName;
    private List<DkVttfSeriesItemDTO> datas;

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public List<DkVttfSeriesItemDTO> getDatas() {
        return datas;
    }

    public void setDatas(List<DkVttfSeriesItemDTO> datas) {
        this.datas = datas;
    }
}
