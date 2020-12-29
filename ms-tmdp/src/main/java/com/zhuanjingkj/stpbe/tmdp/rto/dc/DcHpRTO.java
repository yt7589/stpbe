package com.zhuanjingkj.stpbe.tmdp.rto.dc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

/**
 * 数据中心 =》首页
 */
public class DcHpRTO extends BaseRTO {
    @JSONField(name = "startTime")
    private String startTime; //开始时间
    @JSONField(name = "endTime")
    private String endTime; //结束时间
    @JSONField(name = "category")
    private String category; //类别
    @JSONField(name = "vType")
    private String vType; //车辆类型
    @JSONField(name = "ilType")
    private String ilType; //违章类型
    @JSONField(name = "hphm")
    private String hphm; //车牌号码
    @JSONField(name = "fcId")
    private String fcId; //车脸id
    @JSONField(name = "vAddr")
    private String vAddr; //地点

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return category;
    }

    public void setType(String type) {
        this.category = type;
    }

    public String getvType() {
        return vType;
    }

    public void setvType(String vType) {
        this.vType = vType;
    }

    public String getIlType() {
        return ilType;
    }

    public void setIlType(String ilType) {
        this.ilType = ilType;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getFcId() {
        return fcId;
    }

    public void setFcId(String fcId) {
        this.fcId = fcId;
    }

    public String getvAddr() {
        return vAddr;
    }

    public void setvAddr(String vAddr) {
        this.vAddr = vAddr;
    }
}
