package com.zhuanjingkj.stpbe.data.vo;

public class VehicleHptzVO {
    private String hpzt; // 号牌状态
    private String hpwz; // 号牌位置：x, y, w, h
    private String hpzl; // 号牌种类
    private String hpys; // 号牌颜色样式
    private String hpgg; // 号牌字符格式
    private String hphm; // 号牌号码
    private String hpkxd; // 号牌可信度
    private String mwhpkxd; // 每位号牌可信度
    private String ywlshp; // 有无临时号牌

    public VehicleHptzVO(String hpzt, String hpwz, String hpzl,
                         String hpys, String hpgg, String hphm,
                         String hpkxd, String mwhpkxd, String ywlshp) {
        this.hpzt = hpzt;
        this.hpwz = hpwz;
        this.hpzl = hpzl;
        this.hpys = hpys;
        this.hpgg = hpgg;
        this.hphm = hphm;
        this.hpkxd = hpkxd;
        this.mwhpkxd = mwhpkxd;
        this.ywlshp = ywlshp;
    }

    public String getHpzt() {
        return hpzt;
    }

    public void setHpzt(String hpzt) {
        this.hpzt = hpzt;
    }

    public String getHpwz() {
        return hpwz;
    }

    public void setHpwz(String hpwz) {
        this.hpwz = hpwz;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getHpys() {
        return hpys;
    }

    public void setHpys(String hpys) {
        this.hpys = hpys;
    }

    public String getHpgg() {
        return hpgg;
    }

    public void setHpgg(String hpgg) {
        this.hpgg = hpgg;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getHpkxd() {
        return hpkxd;
    }

    public void setHpkxd(String hpkxd) {
        this.hpkxd = hpkxd;
    }

    public String getMwhpkxd() {
        return mwhpkxd;
    }

    public void setMwhpkxd(String mwhpkxd) {
        this.mwhpkxd = mwhpkxd;
    }

    public String getYwlshp() {
        return ywlshp;
    }

    public void setYwlshp(String ywlshp) {
        this.ywlshp = ywlshp;
    }
}
