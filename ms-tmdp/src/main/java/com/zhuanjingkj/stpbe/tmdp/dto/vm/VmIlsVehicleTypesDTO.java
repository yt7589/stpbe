package com.zhuanjingkj.stpbe.tmdp.dto.vm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 违章监管 =》 车辆类型
 */
public class VmIlsVehicleTypesDTO extends BaseDTO {
    @JSONField(name = "typeId")
    private long typeId;
    @JSONField(name = "typeName")
    private String typeName;

    public VmIlsVehicleTypesDTO(long typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}