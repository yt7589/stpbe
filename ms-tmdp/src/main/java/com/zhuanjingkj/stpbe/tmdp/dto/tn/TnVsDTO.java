package com.zhuanjingkj.stpbe.tmdp.dto.tn;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.TnVsTopVehicleDTO;
import com.zhuanjingkj.stpbe.data.dto.TnVsVehicleDTO;

import java.util.List;

public class TnVsDTO extends BaseDTO {
    @JSONField(name = "tvts")
    List<TnVsTopSiteDTO> tvts; //路网点位过车前10
    @JSONField(name = "tvtv")
    TnVsVehicleDTO tvtv; //路网车辆走势
    @JSONField(name = "tvsd")
    List<TnVsSiteDTO> tvsd; //路网点位

    public List<TnVsTopSiteDTO> getTvts() {
        return tvts;
    }

    public void setTvts(List<TnVsTopSiteDTO> tvts) {
        this.tvts = tvts;
    }

    public TnVsVehicleDTO getTvtv() {
        return tvtv;
    }

    public void setTvtv(TnVsVehicleDTO tvtv) {
        this.tvtv = tvtv;
    }

    public List<TnVsSiteDTO> getTvsd() {
        return tvsd;
    }

    public void setTvsd(List<TnVsSiteDTO> tvsd) {
        this.tvsd = tvsd;
    }
}
