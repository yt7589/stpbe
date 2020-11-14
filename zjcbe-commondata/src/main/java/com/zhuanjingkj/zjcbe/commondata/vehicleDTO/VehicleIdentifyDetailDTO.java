package com.zhuanjingkj.zjcbe.commondata.vehicleDTO;

import com.alibaba.fastjson.annotation.JSONField;

public class VehicleIdentifyDetailDTO {

    @JSONField(name = "SXH")
    private int sxh;

    @JSONField(name = "GCXH")
    private String gcxh;

    @JSONField(name = "WZTZ")
    private WZTZ wztz;

    @JSONField(name = "CXTZ")
    private CXTZ cxtz;

    @JSONField(name = "GXHTZ")
    private GXHTZ gxhtz;

    @JSONField(name = "HPTZ")
    private HPTZ hptz;

    @JSONField(name = "JSXWTZ")
    private JSXWTZ jsxwtz;

    public int getSxh() {
        return sxh;
    }

    public void setSxh(int sxh) {
        this.sxh = sxh;
    }

    public String getGcxh() {
        return gcxh;
    }

    public void setGcxh(String gcxh) {
        this.gcxh = gcxh;
    }

    public WZTZ getWztz() {
        return wztz;
    }

    public void setWztz(WZTZ wztz) {
        this.wztz = wztz;
    }

    public CXTZ getCxtz() {
        return cxtz;
    }

    public void setCxtz(CXTZ cxtz) {
        this.cxtz = cxtz;
    }

    public GXHTZ getGxhtz() {
        return gxhtz;
    }

    public void setGxhtz(GXHTZ gxhtz) {
        this.gxhtz = gxhtz;
    }

    public HPTZ getHptz() {
        return hptz;
    }

    public void setHptz(HPTZ hptz) {
        this.hptz = hptz;
    }

    public JSXWTZ getJsxwtz() {
        return jsxwtz;
    }

    public void setJsxwtz(JSXWTZ jsxwtz) {
        this.jsxwtz = jsxwtz;
    }

    public static class WZTZ {
        @JSONField(name = "PSFX")
        private String psfx;
        @JSONField(name = "CLWZ")
        private String clwz;

        public String getPsfx() {
            return psfx;
        }

        public void setPsfx(String psfx) {
            this.psfx = psfx;
        }

        public String getClwz() {
            return clwz;
        }

        public void setClwz(String clwz) {
            this.clwz = clwz;
        }
    }

    public static class CXTZ {
        @JSONField(name = "PPCX")
        private String ppcx;
        @JSONField(name = "CLLXZFL")
        private String cllxzfl;
        @JSONField(name = "PPXHMS")
        private String ppxhms;
        @JSONField(name = "CSYS")
        private String csys;
        @JSONField(name = "CLPP")
        private String clpp;
        @JSONField(name = "PPXHKXD")
        private String ppxhkxd;
        @JSONField(name = "CLLXFL")
        private String cllxfl;
        @JSONField(name = "CXNK")
        private String cxnk;

        public String getPpcx() {
            return ppcx;
        }

        public void setPpcx(String ppcx) {
            this.ppcx = ppcx;
        }

        public String getCllxzfl() {
            return cllxzfl;
        }

        public void setCllxzfl(String cllxzfl) {
            this.cllxzfl = cllxzfl;
        }

        public String getPpxhms() {
            return ppxhms;
        }

        public void setPpxhms(String ppxhms) {
            this.ppxhms = ppxhms;
        }

        public String getCsys() {
            return csys;
        }

        public void setCsys(String csys) {
            this.csys = csys;
        }

        public String getClpp() {
            return clpp;
        }

        public void setClpp(String clpp) {
            this.clpp = clpp;
        }

        public String getPpxhkxd() {
            return ppxhkxd;
        }

        public void setPpxhkxd(String ppxhkxd) {
            this.ppxhkxd = ppxhkxd;
        }

        public String getCllxfl() {
            return cllxfl;
        }

        public void setCllxfl(String cllxfl) {
            this.cllxfl = cllxfl;
        }

        public String getCxnk() {
            return cxnk;
        }

        public void setCxnk(String cxnk) {
            this.cxnk = cxnk;
        }
    }

    public static class GXHTZ {
        @JSONField(name = "DCJQS")
        private String dcjqs;
        @JSONField(name = "CCZTW")
        private String ccztw;
        @JSONField(name = "GJ")
        private String gj;
        @JSONField(name = "CSZT")
        private String cszt;
        @JSONField(name = "XLJ")
        private String xlj;
        @JSONField(name = "BJ")
        private String bj;
        @JSONField(name = "CSCH")
        private String csch;
        @JSONField(name = "CSPS")
        private String csps;
        @JSONField(name = "CSGH")
        private String csgh;
        @JSONField(name = "TC")
        private String tc;

        public String getDcjqs() {
            return dcjqs;
        }

        public void setDcjqs(String dcjqs) {
            this.dcjqs = dcjqs;
        }

        public String getCcztw() {
            return ccztw;
        }

        public void setCcztw(String ccztw) {
            this.ccztw = ccztw;
        }

        public String getGj() {
            return gj;
        }

        public void setGj(String gj) {
            this.gj = gj;
        }

        public String getCszt() {
            return cszt;
        }

        public void setCszt(String cszt) {
            this.cszt = cszt;
        }

        public String getXlj() {
            return xlj;
        }

        public void setXlj(String xlj) {
            this.xlj = xlj;
        }

        public String getBj() {
            return bj;
        }

        public void setBj(String bj) {
            this.bj = bj;
        }

        public String getCsch() {
            return csch;
        }

        public void setCsch(String csch) {
            this.csch = csch;
        }

        public String getCsps() {
            return csps;
        }

        public void setCsps(String csps) {
            this.csps = csps;
        }

        public String getCsgh() {
            return csgh;
        }

        public void setCsgh(String csgh) {
            this.csgh = csgh;
        }

        public String getTc() {
            return tc;
        }

        public void setTc(String tc) {
            this.tc = tc;
        }
    }

    public static class HPTZ {
        @JSONField(name = "YWLSHP")
        private String ywlshp;
        @JSONField(name = "HPZT")
        private String hpzt;
        @JSONField(name = "HPYS")
        private String hpys;
        @JSONField(name = "HPKXD")
        private String hpkxd;
        @JSONField(name = "MWHPKXD")
        private String mwhpkxd;
        @JSONField(name = "HPGG")
        private String hpgg;
        @JSONField(name = "HPWZ")
        private String hpwz;
        @JSONField(name = "HPZL")
        private String hpzl;
        @JSONField(name = "HPHM")
        private String hphm;

        public String getYwlshp() {
            return ywlshp;
        }

        public void setYwlshp(String ywlshp) {
            this.ywlshp = ywlshp;
        }

        public String getHpzt() {
            return hpzt;
        }

        public void setHpzt(String hpzt) {
            this.hpzt = hpzt;
        }

        public String getHpys() {
            return hpys;
        }

        public void setHpys(String hpys) {
            this.hpys = hpys;
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

        public String getHpgg() {
            return hpgg;
        }

        public void setHpgg(String hpgg) {
            this.hpgg = hpgg;
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

        public String getHphm() {
            return hphm;
        }

        public void setHphm(String hphm) {
            this.hphm = hphm;
        }
    }

    public static class JSXWTZ {
        @JSONField(name = "ZJSKSJ")
        private String zjsksj;
        @JSONField(name = "MTCBDTK")
        private String mtcbdtk;
        @JSONField(name = "ZJSBJAQD")
        private String zjsbjaqd;
        @JSONField(name = "ZJSDDH")
        private String zjsddh;
        @JSONField(name = "FJSZYB")
        private String fjszyb;
        @JSONField(name = "FJSBJAQD")
        private String fjsbjaqd;
        @JSONField(name = "ZJSCY")
        private String zjscy;
        @JSONField(name = "ZJSZYB")
        private String zjszyb;

        public String getZjsksj() {
            return zjsksj;
        }

        public void setZjsksj(String zjsksj) {
            this.zjsksj = zjsksj;
        }

        public String getMtcbdtk() {
            return mtcbdtk;
        }

        public void setMtcbdtk(String mtcbdtk) {
            this.mtcbdtk = mtcbdtk;
        }

        public String getZjsbjaqd() {
            return zjsbjaqd;
        }

        public void setZjsbjaqd(String zjsbjaqd) {
            this.zjsbjaqd = zjsbjaqd;
        }

        public String getZjsddh() {
            return zjsddh;
        }

        public void setZjsddh(String zjsddh) {
            this.zjsddh = zjsddh;
        }

        public String getFjszyb() {
            return fjszyb;
        }

        public void setFjszyb(String fjszyb) {
            this.fjszyb = fjszyb;
        }

        public String getFjsbjaqd() {
            return fjsbjaqd;
        }

        public void setFjsbjaqd(String fjsbjaqd) {
            this.fjsbjaqd = fjsbjaqd;
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
    }
}
