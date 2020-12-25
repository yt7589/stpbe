package com.zhuanjingkj.stpbe.tmdp.rto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class AddVehicleToVcRTO extends BaseRTO {
    @JSONField(name = "hphm")
    private String hphm;

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }
}
