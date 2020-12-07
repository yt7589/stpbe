package com.zhuanjingkj.stpbe.tmdp.rto;

import com.zhuanjingkj.stpbe.data.entity.TrafficViolation;

/**
 * author by guoqiang
 * date on 2020.12.07
 **/
public class TrafficViolationRTO extends TrafficViolation {
    private int pageNum = 1;
    private int pageSize = 10;

    private String violationType;

    private String dateMin;
    private String dateMax;

    private String violationTypeName;

    public String getViolationTypeName() {
        return violationTypeName;
    }

    public void setViolationTypeName(String violationTypeName) {
        this.violationTypeName = violationTypeName;
    }

    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }



    public String getDateMin() {
        return dateMin;
    }

    public void setDateMin(String dateMin) {
        this.dateMin = dateMin;
    }

    public String getDateMax() {
        return dateMax;
    }

    public void setDateMax(String dateMax) {
        this.dateMax = dateMax;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
