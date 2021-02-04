package com.zhuanjingkj.stpbe.tmdp.dto.dc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 数据报告
 * 高峰时段拥堵路名排名
 */
public class DcRtRoadJamDTO extends BaseDTO {
    @JSONField(name = "rdId")
    private long rdId; //路段id
    @JSONField(name = "rdName")
    private String rdName; //路段名称
    @JSONField(name = "srcLng")
    private double srcLng; //开始经度
    @JSONField(name = "srcLat")
    private double srcLat; //开始纬度
    @JSONField(name = "dstLng")
    private double dstLng; //结尾经度
    @JSONField(name = "dstLat")
    private double dstLat; //结尾纬度
    @JSONField(name = "rg")
    private double rg; //环比
    @JSONField(name = "count")
    private double count; //拥堵指数

    public DcRtRoadJamDTO() {
        super();
    }

    public DcRtRoadJamDTO(long rdId, String rdName, double srcLng, double srcLat, double dstLng, double dstLat, double count, double rg) {
        this.rdId = rdId;
        this.rdName = rdName;
        this.srcLng = srcLng;
        this.srcLat = srcLat;
        this.dstLng = dstLng;
        this.dstLat = dstLat;
        this.count = count;
        this.rg = rg;
    }

    public long getRdId() {
        return rdId;
    }

    public void setRdId(long rdId) {
        this.rdId = rdId;
    }

    public String getRdName() {
        return rdName;
    }

    public void setRdName(String rdName) {
        this.rdName = rdName;
    }

    public double getSrcLng() {
        return srcLng;
    }

    public void setSrcLng(double srcLng) {
        this.srcLng = srcLng;
    }

    public double getSrcLat() {
        return srcLat;
    }

    public void setSrcLat(double srcLat) {
        this.srcLat = srcLat;
    }

    public double getDstLng() {
        return dstLng;
    }

    public void setDstLng(double dstLng) {
        this.dstLng = dstLng;
    }

    public double getDstLat() {
        return dstLat;
    }

    public void setDstLat(double dstLat) {
        this.dstLat = dstLat;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getRg() {
        return rg;
    }

    public void setRg(double rg) {
        this.rg = rg;
    }
}
