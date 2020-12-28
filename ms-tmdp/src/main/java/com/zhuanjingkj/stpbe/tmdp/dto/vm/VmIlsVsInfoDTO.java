package com.zhuanjingkj.stpbe.tmdp.dto.vm;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 车辆违章历史信息
 */
public class VmIlsVsInfoDTO {
    @JSONField(name = "hphm")
    private String hphm;
    @JSONField(name = "ilsCount")
    private Integer ilsCount; //累计违章次数
    @JSONField(name = "avCount")
    private Integer avCount; // Annual violation 年度违章
    @JSONField(name = "ilsVstype")
    List<VmIlsVsType> ilsVstype; //违章类别统计
    @JSONField(name = "ilsVsTrend")
    List<VmIlsVsTrend> ilsVsTrend; //违章趋势

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public Integer getIlsCount() {
        return ilsCount;
    }

    public void setIlsCount(Integer ilsCount) {
        this.ilsCount = ilsCount;
    }

    public Integer getAvCount() {
        return avCount;
    }

    public void setAvCount(Integer avCount) {
        this.avCount = avCount;
    }

    public List<VmIlsVsType> getIlsVstype() {
        return ilsVstype;
    }

    public void setIlsVstype(List<VmIlsVsType> ilsVstype) {
        this.ilsVstype = ilsVstype;
    }

    public List<VmIlsVsTrend> getIlsVsTrend() {
        return ilsVsTrend;
    }

    public void setIlsVsTrend(List<VmIlsVsTrend> ilsVsTrend) {
        this.ilsVsTrend = ilsVsTrend;
    }
}
