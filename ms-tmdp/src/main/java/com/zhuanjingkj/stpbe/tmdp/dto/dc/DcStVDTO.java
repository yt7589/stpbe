package com.zhuanjingkj.stpbe.tmdp.dto.dc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.DcStVMDTO;

import java.util.List;

/**
 * 数据中心 =》 数据统计
 * 过车车辆统计
 */
public class DcStVDTO extends BaseDTO {
    @JSONField(name = "dcstVmDTO")
    List<DcStVMDTO> dcstVmDTO;
    @JSONField(name = "today_st")
    private Integer today_st; //本日车辆
    @JSONField(name = "week_st")
    private Integer week_st; //本周车辆
    @JSONField(name = "month_st")
    private Integer month_st; //本月车辆

    public Integer getToday_st() {
        return today_st;
    }

    public void setToday_st(Integer today_st) {
        this.today_st = today_st;
    }

    public Integer getWeek_st() {
        return week_st;
    }

    public void setWeek_st(Integer week_st) {
        this.week_st = week_st;
    }

    public Integer getMonth_st() {
        return month_st;
    }

    public void setMonth_st(Integer month_st) {
        this.month_st = month_st;
    }

    public List<DcStVMDTO> getDcstVmDTO() {
        return dcstVmDTO;
    }

    public void setDcstVmDTO(List<DcStVMDTO> dcstVmDTO) {
        this.dcstVmDTO = dcstVmDTO;
    }
}
