package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DkMainDTO extends BaseDTO {
    @JSONField(name = "dkVtie")
    private DkVtieDTO dkVtie;
    @JSONField(name = "dkVtp")
    private DkVtpDTO dkVtp;
    @JSONField(name = "dkTitf")
    private DkTitfDTO dkTitf;

    public DkVtieDTO getDkVtie() {
        return dkVtie;
    }

    public void setDkVtie(DkVtieDTO dkVtie) {
        this.dkVtie = dkVtie;
    }

    public DkVtpDTO getDkVtp() {
        return dkVtp;
    }

    public void setDkVtp(DkVtpDTO dkVtp) {
        this.dkVtp = dkVtp;
    }

    public DkTitfDTO getDkTitf() {
        return dkTitf;
    }

    public void setDkTitf(DkTitfDTO dkTitf) {
        this.dkTitf = dkTitf;
    }
}
