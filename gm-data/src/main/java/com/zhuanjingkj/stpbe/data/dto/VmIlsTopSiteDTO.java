package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 违章分布 =》 违章点位统计
 */
public class VmIlsTopSiteDTO extends BaseDTO {
    @JSONField(name = "siteId")
    private long siteId;
    @JSONField(name = "siteName")
    private String siteName;
    @JSONField(name = "count")
    private Integer count;

    public VmIlsTopSiteDTO() {
        super();
    }

    public VmIlsTopSiteDTO(long siteId, String siteName, Integer count) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.count = count;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
