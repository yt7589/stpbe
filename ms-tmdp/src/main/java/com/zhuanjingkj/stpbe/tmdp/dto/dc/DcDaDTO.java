package com.zhuanjingkj.stpbe.tmdp.dto.dc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class DcDaDTO extends BaseDTO {
    @JSONField(name = "total_recognition")
    private Integer total_recognition; //累计识别量
    @JSONField(name = "total_violation")
    private Integer total_violation; //累计违章量
    @JSONField(name = "total_violation_city")
    private Integer total_violation_city; //累计本市违章量
    @JSONField(name = "total_violation_town")
    private Integer total_violation_town; //累计外埠违章量
    @JSONField(name = "dit")
    List<DcIlTrendDTO> dit; //违章趋势
    @JSONField(name = "drt")
    List<DcRgTrendDTO> drt; //识别量趋势

    public Integer getTotal_recognition() {
        return total_recognition;
    }

    public void setTotal_recognition(Integer total_recognition) {
        this.total_recognition = total_recognition;
    }

    public Integer getTotal_violation() {
        return total_violation;
    }

    public void setTotal_violation(Integer total_violation) {
        this.total_violation = total_violation;
    }

    public Integer getTotal_violation_city() {
        return total_violation_city;
    }

    public void setTotal_violation_city(Integer total_violation_city) {
        this.total_violation_city = total_violation_city;
    }

    public Integer getTotal_violation_town() {
        return total_violation_town;
    }

    public void setTotal_violation_town(Integer total_violation_town) {
        this.total_violation_town = total_violation_town;
    }

    public List<DcIlTrendDTO> getDit() {
        return dit;
    }

    public void setDit(List<DcIlTrendDTO> dit) {
        this.dit = dit;
    }

    public List<DcRgTrendDTO> getDrt() {
        return drt;
    }

    public void setDrt(List<DcRgTrendDTO> drt) {
        this.drt = drt;
    }
}
