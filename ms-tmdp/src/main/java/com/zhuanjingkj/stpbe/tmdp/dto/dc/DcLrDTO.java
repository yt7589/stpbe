package com.zhuanjingkj.stpbe.tmdp.dto.dc;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;


public class DcLrDTO extends BaseDTO {
    List<DcLrSiteDTO> lrSite;

    public List<DcLrSiteDTO> getLrSite() {
        return lrSite;
    }

    public void setLrSite(List<DcLrSiteDTO> lrSite) {
        this.lrSite = lrSite;
    }
}
