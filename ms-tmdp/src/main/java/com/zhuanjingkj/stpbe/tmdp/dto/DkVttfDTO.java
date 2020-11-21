package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class DkVttfDTO extends BaseDTO {
    @JSONField(name = "serieses")
    private List<DkVttfSeriesDTO> serieses;

    public List<DkVttfSeriesDTO> getSerieses() {
        return serieses;
    }

    public void setSerieses(List<DkVttfSeriesDTO> serieses) {
        this.serieses = serieses;
    }
}
