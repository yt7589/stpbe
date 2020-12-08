package com.zhuanjingkj.stpbe.tmdp.dto.res;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.RegionDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.camera.SiteDTO;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.12.08
 **/
public class TrafficViolationStatisticListDTO extends BaseDTO {

    private List<RegionDTO> regionList;
    private List<SiteDTO> siteList;

    public List<RegionDTO> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<RegionDTO> regionList) {
        this.regionList = regionList;
    }

    public List<SiteDTO> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<SiteDTO> siteList) {
        this.siteList = siteList;
    }
}
