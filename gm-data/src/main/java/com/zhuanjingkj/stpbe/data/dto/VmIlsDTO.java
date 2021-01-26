package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class VmIlsDTO extends BaseDTO {
    @JSONField(name = "tvisJsonId")
    private long tvisJsonId;
    @JSONField(name = "tvisJsonTbl")
    private String tvisJsonTbl;
    @JSONField(name = "ilId")
    private long ilId; //违章id
    @JSONField(name = "ilTime")
    private String ilTime; //违章时间
    @JSONField(name = "ilAddr")
    private String ilAddr; //违章地点
    @JSONField(name = "hmhp")
    private String hmhp; //车牌号
    @JSONField(name = "category")
    private String category; //类别
    @JSONField(name = "types")
    private String types; //车辆类型
    @JSONField(name = "ilTypes")
    private String ilTypes; //违章原因
    @JSONField(name = "imageId")
    private long imageId; //图片id
    @JSONField(name = "imageUrl")
    private String imageUrl; //图片路径

    public VmIlsDTO() {
        super();
    }

    public VmIlsDTO(long ilId, String ilTime, String ilAddr, String hmhp, String category, String types, String ilTypes, long imageId, String imageUrl) {
        this.ilId = ilId;
        this.ilTime = ilTime;
        this.ilAddr = ilAddr;
        this.hmhp = hmhp;
        this.category = category;
        this.types = types;
        this.ilTypes = ilTypes;
        this.imageId = imageId;
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

    public String getHmhp() {
        return hmhp;
    }

    public void setHmhp(String hmhp) {
        this.hmhp = hmhp;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getIlTypes() {
        return ilTypes;
    }

    public void setIlTypes(String ilTypes) {
        this.ilTypes = ilTypes;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
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
}
