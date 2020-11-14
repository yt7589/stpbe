package com.zhuanjingkj.zjcbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class SystemInfoDTO extends BaseDTO {
    @JSONField(name="systemName")
    private String systemName;
    @JSONField(name="version")
    private String version;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
