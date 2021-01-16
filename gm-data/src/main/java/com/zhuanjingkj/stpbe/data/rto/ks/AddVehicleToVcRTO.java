package com.zhuanjingkj.stpbe.data.rto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class AddVehicleToVcRTO extends BaseRTO {
    @JSONField(name = "vcId")
    private Integer vcId;

    @JSONField(name = "hphm")
    private String hphm;

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public Integer getVcId() {
        return vcId;
    }

    public void setVcId(Integer vcId) {
        this.vcId = vcId;
    }
}
