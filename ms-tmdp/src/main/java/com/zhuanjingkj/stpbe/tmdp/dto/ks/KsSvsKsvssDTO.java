package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class KsSvsKsvssDTO extends BaseDTO {
    @JSONField(name = "siteId")
    private int siteId;
    @JSONField(name = "siteName")
    private String siteName;
    @JSONField(name = "count")
    private long count;

    public KsSvsKsvssDTO(int siteId, String siteName, long count) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.count = count;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
