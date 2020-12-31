package com.zhuanjingkj.stpbe.tmdp.dto.dm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DmDeviceTypeDTO extends BaseDTO {
    @JSONField(name = "dtId")
    private String dtId; //设备类型id
    @JSONField(name = "dtName")
    private String dtName; //设备类型名称

    public DmDeviceTypeDTO(String dtId, String dtName) {
        this.dtId = dtId;
        this.dtName = dtName;
    }

    public String getDtId() {
        return dtId;
    }

    public void setDtId(String dtId) {
        this.dtId = dtId;
    }

    public String getDtName() {
        return dtName;
    }

    public void setDtName(String dtName) {
        this.dtName = dtName;
    }
}
