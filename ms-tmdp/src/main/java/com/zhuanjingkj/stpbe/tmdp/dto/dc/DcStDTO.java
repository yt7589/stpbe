package com.zhuanjingkj.stpbe.tmdp.dto.dc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

/**
 * 数据中心 =》 数据统计
 */
public class DcStDTO extends BaseDTO {
    @JSONField(name = "dcSys")
    private DcStSysDTO dcSys; //系统对象
    @JSONField(name = "dcToday")
    private DcStTodayDTO dcToday; //今日概况
    @JSONField(name = "dcVSt")
    List<DcStVDTO> dcVSt; //过车量统计
    @JSONField(name = "dcVArea")
    List<DcStVAreaDTO> dcVArea; //过车量地区前5
    @JSONField(name = "dcVSite")
    List<DcStVSiteDTO> dcVSite; //过车量点位前5
    @JSONField(name = "dcVTrend")
    List<DcStVTrendDTO> dcVTrend; //过车量7日走势图
    @JSONField(name = "dcIlSite")
    List<DcStIlSiteDTO> dcIlSite; //违法点位前7
    @JSONField(name = "dcKvSite")
    List<DcStKvSiteDTO> dcKvSite; //重点车辆违法点位前7
    @JSONField(name = "dcTsSite")
    List<DcStTruckSiteDTO> dcTsSite; //大货车点位前7

    public DcStSysDTO getDcSys() {
        return dcSys;
    }

    public void setDcSys(DcStSysDTO dcSys) {
        this.dcSys = dcSys;
    }

    public DcStTodayDTO getDcToday() {
        return dcToday;
    }

    public void setDcToday(DcStTodayDTO dcToday) {
        this.dcToday = dcToday;
    }

    public List<DcStVDTO> getDcVSt() {
        return dcVSt;
    }

    public void setDcVSt(List<DcStVDTO> dcVSt) {
        this.dcVSt = dcVSt;
    }

    public List<DcStVAreaDTO> getDcVArea() {
        return dcVArea;
    }

    public void setDcVArea(List<DcStVAreaDTO> dcVArea) {
        this.dcVArea = dcVArea;
    }

    public List<DcStVSiteDTO> getDcVSite() {
        return dcVSite;
    }

    public void setDcVSite(List<DcStVSiteDTO> dcVSite) {
        this.dcVSite = dcVSite;
    }

    public List<DcStVTrendDTO> getDcVTrend() {
        return dcVTrend;
    }

    public void setDcVTrend(List<DcStVTrendDTO> dcVTrend) {
        this.dcVTrend = dcVTrend;
    }

    public List<DcStIlSiteDTO> getDcIlSite() {
        return dcIlSite;
    }

    public void setDcIlSite(List<DcStIlSiteDTO> dcIlSite) {
        this.dcIlSite = dcIlSite;
    }

    public List<DcStKvSiteDTO> getDcKvSite() {
        return dcKvSite;
    }

    public void setDcKvSite(List<DcStKvSiteDTO> dcKvSite) {
        this.dcKvSite = dcKvSite;
    }

    public List<DcStTruckSiteDTO> getDcTsSite() {
        return dcTsSite;
    }

    public void setDcTsSite(List<DcStTruckSiteDTO> dcTsSite) {
        this.dcTsSite = dcTsSite;
    }
}
