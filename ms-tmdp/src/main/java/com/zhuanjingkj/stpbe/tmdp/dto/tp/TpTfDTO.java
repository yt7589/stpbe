package com.zhuanjingkj.stpbe.tmdp.dto.tp;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.TpTfSiteDTO;
import com.zhuanjingkj.stpbe.data.dto.TpTfStDTO;

import java.util.List;

public class TpTfDTO extends BaseDTO {
    @JSONField(name = "tVehicle")
    private Integer tVehicle; //车流量
    @JSONField(name = "tfst")
    List<TpTfStDTO> tfst;
    @JSONField(name ="tfs")
    List<TpTfSiteDTO> tfs;

    public Integer gettVehicle() {
        return tVehicle;
    }

    public void settVehicle(Integer tVehicle) {
        this.tVehicle = tVehicle;
    }

    public List<TpTfStDTO> getTfst() {
        return tfst;
    }

    public void setTfst(List<TpTfStDTO> tfst) {
        this.tfst = tfst;
    }

    public List<TpTfSiteDTO> getTfs() {
        return tfs;
    }

    public void setTfs(List<TpTfSiteDTO> tfs) {
        this.tfs = tfs;
    }
}
