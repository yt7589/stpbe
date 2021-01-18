package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.KsLpsLalpDTO;
import com.zhuanjingkj.stpbe.data.dto.KsLpsSiteDTO;

import java.util.List;

public class KsLpsDTO extends BaseDTO {
    @JSONField(name = "wpCount")
    private Integer wpCount; //无牌
    @JSONField(name = "tpCount")
    private Integer tpCount; //套牌
    @JSONField(name = "jpCount")
    private Integer jpCount; //假牌
    @JSONField(name = "hpzdCount")
    private Integer hpzdCount; //号牌遮挡
    @JSONField(name = "Lalp")
    List<KsLpsLalpDTO> Lalp;
    @JSONField(name = "site")
    List<KsLpsSiteDTO> site;
    @JSONField(name = "area")
    List<KsLpsAreaDTO> area;
    @JSONField(name = "time")
    List<KsLpsTimeDTO> time;

    public Integer getWpCount() {
        return wpCount;
    }

    public void setWpCount(Integer wpCount) {
        this.wpCount = wpCount;
    }

    public Integer getTpCount() {
        return tpCount;
    }

    public void setTpCount(Integer tpCount) {
        this.tpCount = tpCount;
    }

    public Integer getJpCount() {
        return jpCount;
    }

    public void setJpCount(Integer jpCount) {
        this.jpCount = jpCount;
    }

    public Integer getHpzdCount() {
        return hpzdCount;
    }

    public void setHpzdCount(Integer hpzdCount) {
        this.hpzdCount = hpzdCount;
    }

    public List<KsLpsLalpDTO> getLalp() {
        return Lalp;
    }

    public void setLalp(List<KsLpsLalpDTO> lalp) {
        Lalp = lalp;
    }

    public List<KsLpsSiteDTO> getSite() {
        return site;
    }

    public void setSite(List<KsLpsSiteDTO> site) {
        this.site = site;
    }

    public List<KsLpsAreaDTO> getArea() {
        return area;
    }

    public void setArea(List<KsLpsAreaDTO> area) {
        this.area = area;
    }

    public List<KsLpsTimeDTO> getTime() {
        return time;
    }

    public void setTime(List<KsLpsTimeDTO> time) {
        this.time = time;
    }
}
