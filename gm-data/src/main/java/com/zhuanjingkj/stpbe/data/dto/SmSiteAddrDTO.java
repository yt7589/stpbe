package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class SmSiteAddrDTO extends BaseDTO {
    @JSONField(name = "siteAddrId")
    private long siteAddrId;
    @JSONField(name = "siteAddrName")
    private String siteAddrName;

    public SmSiteAddrDTO(long siteAddrId, String siteAddrName) {
        this.siteAddrId = siteAddrId;
        this.siteAddrName = siteAddrName;
    }

    public long getSiteAddrId() {
        return siteAddrId;
    }

    public void setSiteAddrId(long siteAddrId) {
        this.siteAddrId = siteAddrId;
    }

    public String getSiteAddrName() {
        return siteAddrName;
    }

    public void setSiteAddrName(String siteAddrName) {
        this.siteAddrName = siteAddrName;
    }
}
