package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class KsSvsDTO extends BaseDTO {
    @JSONField(name = "htfs")
    private KsSvsHtfsDTO htfs;

    public KsSvsHtfsDTO getHtfs() {
        return htfs;
    }

    public void setHtfs(KsSvsHtfsDTO htfs) {
        this.htfs = htfs;
    }
}
