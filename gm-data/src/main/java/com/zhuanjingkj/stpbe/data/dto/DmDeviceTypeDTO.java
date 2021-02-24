package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DmDeviceTypeDTO extends BaseDTO {
    @JSONField(name = "dtId")
    private long dtId; //设备类型id
    @JSONField(name = "dtName")
    private String dtName; //设备类型名称

    public DmDeviceTypeDTO() {
        super();
    }

    public DmDeviceTypeDTO(long dtId, String dtName) {
        this.dtId = dtId;
        this.dtName = dtName;
    }

    public long getDtId() {
        return dtId;
    }

    public void setDtId(long dtId) {
        this.dtId = dtId;
    }

    public String getDtName() {
        return dtName;
    }

    public void setDtName(String dtName) {
        this.dtName = dtName;
    }
}
