package com.zhuanjingkj.stpbe.tmdp.dto.dc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

/**
 * 数据中心 =》 数据统计
 */
public class DcStDTO extends BaseDTO {
    @JSONField(name = "dcSys")
    private DcSysDTO dcSys; //系统对象
    @JSONField(name = "dcToday")
    private DcTodayDTO dcToday; //今日概况
    @JSONField(name = "dcVSt")
    List<DcVstDTO> dcVSt; //过车量统计
    @JSONField(name = "dcVArea")
    List<DcVAreaDTO> dcVArea; //过车量地区前5
    @JSONField(name = "dcVSite")
    List<DcVSiteDTO> dcVSite; //过车量点位前5
    @JSONField(name = "dcVTrend")
    List<DcVTrendDTO> dcVTrend; //过车量7日走势图
    @JSONField(name = "dcIlSite")
    List<DcIlSiteDTO> dcIlSite; //违法点位前7
    @JSONField(name = "dcKvSite")
    List<DcKvSiteDTO> dcKvSite; //重点车辆违法点位前7
    @JSONField(name = "dcTsSite")
    List<DcTruckSiteDTO> dcTsSite; //大货车点位前7

    public DcSysDTO getDcSys() {
        return dcSys;
    }

    public void setDcSys(DcSysDTO dcSys) {
        this.dcSys = dcSys;
    }

    public DcTodayDTO getDcToday() {
        return dcToday;
    }

    public void setDcToday(DcTodayDTO dcToday) {
        this.dcToday = dcToday;
    }

    public List<DcVstDTO> getDcVSt() {
        return dcVSt;
    }

    public void setDcVSt(List<DcVstDTO> dcVSt) {
        this.dcVSt = dcVSt;
    }

    public List<DcVAreaDTO> getDcVArea() {
        return dcVArea;
    }

    public void setDcVArea(List<DcVAreaDTO> dcVArea) {
        this.dcVArea = dcVArea;
    }

    public List<DcVSiteDTO> getDcVSite() {
        return dcVSite;
    }

    public void setDcVSite(List<DcVSiteDTO> dcVSite) {
        this.dcVSite = dcVSite;
    }

    public List<DcVTrendDTO> getDcVTrend() {
        return dcVTrend;
    }

    public void setDcVTrend(List<DcVTrendDTO> dcVTrend) {
        this.dcVTrend = dcVTrend;
    }

    public List<DcIlSiteDTO> getDcIlSite() {
        return dcIlSite;
    }

    public void setDcIlSite(List<DcIlSiteDTO> dcIlSite) {
        this.dcIlSite = dcIlSite;
    }

    public List<DcKvSiteDTO> getDcKvSite() {
        return dcKvSite;
    }

    public void setDcKvSite(List<DcKvSiteDTO> dcKvSite) {
        this.dcKvSite = dcKvSite;
    }

    public List<DcTruckSiteDTO> getDcTsSite() {
        return dcTsSite;
    }

    public void setDcTsSite(List<DcTruckSiteDTO> dcTsSite) {
        this.dcTsSite = dcTsSite;
    }
}
