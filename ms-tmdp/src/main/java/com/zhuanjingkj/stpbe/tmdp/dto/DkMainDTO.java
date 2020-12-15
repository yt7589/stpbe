package com.zhuanjingkj.stpbe.tmdp.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class DkMainDTO extends BaseDTO {
    @JSONField(name = "dkVtie")
    private DkVtieDTO dkVtie;
    @JSONField(name = "dkVtp")
    private DkVtpDTO dkVtp;
    @JSONField(name = "dkTitf")
    private DkTitfDTO dkTitf;
    @JSONField(name = "dkVttf")
    private List<DkVttfSeriesDTO> dkVttf;
    @JSONField(name = "dkTjrss")
    private List<DkTjrsItemDTO> dkTjrss;
    @JSONField(name = "dkDctfs")
    private List<DkDctfItemDTO> dkDctfs;

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

    public List<DkVttfSeriesDTO> getDkVttf() {
        return dkVttf;
    }

    public void setDkVttf(List<DkVttfSeriesDTO> dkVttf) {
        this.dkVttf = dkVttf;
    }

    public List<DkTjrsItemDTO> getDkTjrss() {
        return dkTjrss;
    }

    public void setDkTjrss(List<DkTjrsItemDTO> dkTjrss) {
        this.dkTjrss = dkTjrss;
    }

    public List<DkDctfItemDTO> getDkDctfs() {
        return dkDctfs;
    }

    public void setDkDctfs(List<DkDctfItemDTO> dkDctfs) {
        this.dkDctfs = dkDctfs;
    }
}
