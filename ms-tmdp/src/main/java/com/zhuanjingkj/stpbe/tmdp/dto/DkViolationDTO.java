package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.DkMvtsDTO;
import com.zhuanjingkj.stpbe.data.dto.DkRtvrDTO;

import java.util.List;

public class DkViolationDTO extends BaseDTO {
    @JSONField(name = "dkRtvrs")
    List<DkRtvrDTO> dkRtvrs;
    @JSONField(name = "dkMvtss")
    List<DkMvtsDTO> dkMvtss;
    @JSONField(name = "dkTvts")
    List<DkTvtDTO> dkTvts;

    public List<DkRtvrDTO> getDkRtvrs() {
        return dkRtvrs;
    }

    public void setDkRtvrs(List<DkRtvrDTO> dkRtvrs) {
        this.dkRtvrs = dkRtvrs;
    }

    public List<DkMvtsDTO> getDkMvtss() {
        return dkMvtss;
    }

    public void setDkMvtss(List<DkMvtsDTO> dkMvtss) {
        this.dkMvtss = dkMvtss;
    }

    public List<DkTvtDTO> getDkTvts() {
        return dkTvts;
    }

    public void setDkTvts(List<DkTvtDTO> dkTvts) {
        this.dkTvts = dkTvts;
    }
}
