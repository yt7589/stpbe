package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 设备管理 =》 空间管理
 * 地区列表
 */
public class DmAmAreaDTO extends BaseDTO {
    @JSONField(name = "areaId")
    private long areaId; //区域id
    @JSONField(name = "areaName")
    private String areaName; //区域名称
    @JSONField(name = "parentCode")
    private String parentCode; // 区域父节点code
    @JSONField(name = "parentName")
    private String parentName; // 区域父节点名称

    public DmAmAreaDTO() {
        super();
    }

    public DmAmAreaDTO(long areaId, String areaName, String parentCode, String parentName) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.parentCode = parentCode;
        this.parentName = parentName;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
