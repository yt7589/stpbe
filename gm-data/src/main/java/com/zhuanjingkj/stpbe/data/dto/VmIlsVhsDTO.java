package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 违章监管 =》车辆违章历史列表
 */
public class VmIlsVhsDTO extends BaseDTO {
    @JSONField(name = "tvisJsonId")
    private long tvisJsonId;
    @JSONField(name = "tvisJsonTbl")
    private String tvisJsonTbl;
    @JSONField(name = "ilId")
    private long ilId;
    @JSONField(name = "ilTime")
    private String ilTime;
    @JSONField(name = "ilAddr")
    private String ilAddr;
    @JSONField(name = "ilType")
    private String ilType;
    @JSONField(name = "imageUrl")
    private String imageUrl;
    @JSONField(name = "vehsIdx")
    private long vehsIdx;

    public VmIlsVhsDTO() {
        super();
    }

    public VmIlsVhsDTO(long ilId, String ilTime, String ilAddr, String ilType, String imageUrl) {
        this.ilId = ilId;
        this.ilTime = ilTime;
        this.ilAddr = ilAddr;
        this.ilType = ilType;
        this.imageUrl = imageUrl;
    }

    public long getIlId() {
        return ilId;
    }

    public void setIlId(long ilId) {
        this.ilId = ilId;
    }

    public String getIlTime() {
        return ilTime;
    }

    public void setIlTime(String ilTime) {
        this.ilTime = ilTime;
    }

    public String getIlAddr() {
        return ilAddr;
    }

    public void setIlAddr(String ilAddr) {
        this.ilAddr = ilAddr;
    }

    public String getIlType() {
        return ilType;
    }

    public void setIlType(String ilType) {
        this.ilType = ilType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getTvisJsonId() {
        return tvisJsonId;
    }

    public void setTvisJsonId(long tvisJsonId) {
        this.tvisJsonId = tvisJsonId;
    }

    public String getTvisJsonTbl() {
        return tvisJsonTbl;
    }

    public void setTvisJsonTbl(String tvisJsonTbl) {
        this.tvisJsonTbl = tvisJsonTbl;
    }

    public long getVehsIdx() {
        return vehsIdx;
    }

    public void setVehsIdx(long vehsIdx) {
        this.vehsIdx = vehsIdx;
    }
}
