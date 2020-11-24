package com.zhuanjingkj.stpbe.tmdp.dto;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on 2020.11.23
 **/
public class EmphasisVehicleTypeDTO extends BaseDTO {

    /**
     * 重点车辆类型名称
     */
    private String emphasisVehicleTypeName;

    /**
     * 重点车辆占比
     */
    private String percentage;

    public String getEmphasisVehicleTypeName() {
        return emphasisVehicleTypeName;
    }

    public void setEmphasisVehicleTypeName(String emphasisVehicleTypeName) {
        this.emphasisVehicleTypeName = emphasisVehicleTypeName;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
