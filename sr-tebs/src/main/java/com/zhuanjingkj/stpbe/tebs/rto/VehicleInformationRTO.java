package com.zhuanjingkj.stpbe.tebs.rto;

import com.zhuanjingkj.stpbe.data.rto.BaseRTO;
import com.zhuanjingkj.stpbe.data.entity.image.VehicleInformation;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.12.03
 **/
public class VehicleInformationRTO extends BaseRTO {
    private String vehicleInformationTableName;

    private List<VehicleInformation> vehicleInformationList;

    public List<VehicleInformation> getVehicleInformationList() {
        return vehicleInformationList;
    }

    public void setVehicleInformationList(List<VehicleInformation> vehicleInformationList) {
        this.vehicleInformationList = vehicleInformationList;
    }

    public String getVehicleInformationTableName() {
        return vehicleInformationTableName;
    }

    public void setVehicleInformationTableName(String vehicleInformationTableName) {
        this.vehicleInformationTableName = vehicleInformationTableName;
    }
}
