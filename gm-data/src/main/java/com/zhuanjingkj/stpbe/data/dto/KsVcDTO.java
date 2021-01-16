package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class KsVcDTO extends BaseDTO {
    @JSONField(name = "vcId")
    private long vcId;
    @JSONField(name = "hphm")
    private String hphm;

    public KsVcDTO(long vcId, String hphm) {
        this.vcId = vcId;
        this.hphm = hphm;
    }

    public long getVcId() {
        return vcId;
    }

    public void setVcId(long vcId) {
        this.vcId = vcId;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }
}
