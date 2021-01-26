package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DcHpDTO extends BaseDTO {
    @JSONField(name = "dcId")
    private long dcId;
    @JSONField(name = "tvisJsonId")
    private long tvisJsonId;
    @JSONField(name = "tvisJsonTbl")
    private String tvisJsonTbl;
    @JSONField(name = "dcTime")
    private String dcTime;
    @JSONField(name = "dcAddr")
    private String dcAddr;
    @JSONField(name = "hphm")
    private String hphm;
    @JSONField(name = "category")
    private String category;
    @JSONField(name = "isIl")
    private String isIl;
    @JSONField(name = "ilType")
    private String ilType;
    @JSONField(name = "imageUrl")
    private String imageUrl;

    public DcHpDTO(long dcId, String dcTime, String dcAddr, String hphm, String category, String isIl, String ilType, String imageUrl) {
        this.dcId = dcId;
        this.dcTime = dcTime;
        this.dcAddr = dcAddr;
        this.hphm = hphm;
        this.category = category;
        this.isIl = isIl;
        this.ilType = ilType;
        this.imageUrl = imageUrl;
    }

    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("dcId", dcId);
            obj.put("dcTime", dcTime);
            obj.put("dcAddr", dcAddr);
            obj.put("hphm", hphm);
            obj.put("category", category);
            obj.put("isIl", isIl);
            obj.put("ilType", ilType);
            obj.put("imageUrl", imageUrl);
        } catch (JSONException ex) {
        }
        return obj;
    }
    public long getDcId() {
        return dcId;
    }

    public void setDcId(long dcId) {
        this.dcId = dcId;
    }

    public String getDcTime() {
        return dcTime;
    }

    public void setDcTime(String dcTime) {
        this.dcTime = dcTime;
    }

    public String getDcAddr() {
        return dcAddr;
    }

    public void setDcAddr(String dcAddr) {
        this.dcAddr = dcAddr;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIsIl() {
        return isIl;
    }

    public void setIsIl(String isIl) {
        this.isIl = isIl;
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
}
