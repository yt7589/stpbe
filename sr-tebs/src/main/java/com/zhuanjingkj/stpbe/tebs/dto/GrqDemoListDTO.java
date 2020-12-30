package com.zhuanjingkj.stpbe.tebs.dto;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class GrqDemoListDTO extends BaseDTO {
    private List<GrqDemoDTO> recs;

    public List<GrqDemoDTO> getRecs() {
        return recs;
    }

    public void setRecs(List<GrqDemoDTO> recs) {
        this.recs = recs;
    }
}
