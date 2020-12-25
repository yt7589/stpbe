package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class KsLpsDTO extends BaseDTO {
    @JSONField(name = "Lalp")
    List<KsLpsLalpDTO> Lalp;
    @JSONField(name = "site")
    List<KsLpsSiteDTO> site;
    @JSONField(name = "area")
    List<KsLpsAreaDTO> area;
    @JSONField(name = "time")
    List<KsLpsTimeDTO> time;

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
