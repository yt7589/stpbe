package com.zhuanjingkj.stpbe.tmdp.dto.vm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class IlsTopDTO extends BaseDTO {
    @JSONField(name = "ilsAreaDTO")
    List<IlsTopAreaDTO> ilsTopAreaDTO;
    @JSONField(name = "ilsSiteDTO")
    List<IlsTopSiteDTO> ilsTopSiteDTO;

    public List<IlsTopAreaDTO> getIlsAreaDTO() {
        return ilsTopAreaDTO;
    }

    public void setIlsAreaDTO(List<IlsTopAreaDTO> ilsTopAreaDTO) {
        this.ilsTopAreaDTO = ilsTopAreaDTO;
    }

    public List<IlsTopSiteDTO> getIlsSiteDTO() {
        return ilsTopSiteDTO;
    }

    public void setIlsSiteDTO(List<IlsTopSiteDTO> ilsTopSiteDTO) {
        this.ilsTopSiteDTO = ilsTopSiteDTO;
    }
}
