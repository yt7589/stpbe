package com.zhuanjingkj.stpbe.tmdp.dto.tn;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class TnVsDTO extends BaseDTO {
    @JSONField(name = "tvts")
    List<TnVsTopSiteDTO> tvts; //路网点位过车前10
    @JSONField(name = "tvtv")
    List<TnVsTopVehicleDTO> tvtv; //路网车辆走势
    @JSONField(name = "tvsd")
    List<TnVsSiteDTO> tvsd; //路网点位

    public List<TnVsTopSiteDTO> getTvts() {
        return tvts;
    }

    public void setTvts(List<TnVsTopSiteDTO> tvts) {
        this.tvts = tvts;
    }

    public List<TnVsTopVehicleDTO> getTvtv() {
        return tvtv;
    }

    public void setTvtv(List<TnVsTopVehicleDTO> tvtv) {
        this.tvtv = tvtv;
    }

    public List<TnVsSiteDTO> getTvsd() {
        return tvsd;
    }

    public void setTvsd(List<TnVsSiteDTO> tvsd) {
        this.tvsd = tvsd;
    }
}
