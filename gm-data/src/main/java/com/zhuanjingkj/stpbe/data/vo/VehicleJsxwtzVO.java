package com.zhuanjingkj.stpbe.data.vo;

import com.alibaba.fastjson.JSONObject;

/**
 * 驾驶行为特征
 */
public class VehicleJsxwtzVO {
    private String zjsddh; //主驾驶打电话
    private String zjsksj; //主驾驶看手机
    private String zjsbjaqd; //主驾驶不系安全带
    private String zjscy; //主驾驶抽烟
    private String zjszyb; //主驾驶放下遮阳板
    private String fjsbjaqd; //副驾驶不系安全带
    private String fjszyb; //副驾驶放下遮阳板
    private String mtcbdtk; //摩托车不戴头盔

    public JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("zjsddh", zjsddh);
        obj.put("zjsksj", zjsksj);
        obj.put("zjsbjaqd", zjsbjaqd);
        obj.put("zjscy", zjscy);
        obj.put("zjszyb", zjszyb);
        obj.put("fjsbjaqd", fjsbjaqd);
        obj.put("fjszyb", fjszyb);
        obj.put("mtcbdtc", mtcbdtk);
        return obj;
    }
    public String getZjsddh() {
        return zjsddh;
    }

    public void setZjsddh(String zjsddh) {
        this.zjsddh = zjsddh;
    }

    public String getZjsksj() {
        return zjsksj;
    }

    public void setZjsksj(String zjsksj) {
        this.zjsksj = zjsksj;
    }

    public String getZjsbjaqd() {
        return zjsbjaqd;
    }

    public void setZjsbjaqd(String zjsbjaqd) {
        this.zjsbjaqd = zjsbjaqd;
    }

    public String getZjscy() {
        return zjscy;
    }

    public void setZjscy(String zjscy) {
        this.zjscy = zjscy;
    }

    public String getZjszyb() {
        return zjszyb;
    }

    public void setZjszyb(String zjszyb) {
        this.zjszyb = zjszyb;
    }

    public String getFjsbjaqd() {
        return fjsbjaqd;
    }

    public void setFjsbjaqd(String fjsbjaqd) {
        this.fjsbjaqd = fjsbjaqd;
    }

    public String getFjszyb() {
        return fjszyb;
    }

    public void setFjszyb(String fjszyb) {
        this.fjszyb = fjszyb;
    }

    public String getMtcbdtk() {
        return mtcbdtk;
    }

    public void setMtcbdtk(String mtcbdtc) {
        this.mtcbdtk = mtcbdtc;
    }
}
