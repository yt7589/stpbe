package com.zhuanjingkj.stpbe.data.dto;

import java.util.List;

public class TnVsVehicleDTO {
    List<TnVsTopVehicleDTO> tsfvs;
    List<TnVsTopVehicleDTO> ysfvs;

    public List<TnVsTopVehicleDTO> getTsfvs() {
        return tsfvs;
    }

    public void setTsfvs(List<TnVsTopVehicleDTO> tsfvs) {
        this.tsfvs = tsfvs;
    }

    public List<TnVsTopVehicleDTO> getYsfvs() {
        return ysfvs;
    }

    public void setYsfvs(List<TnVsTopVehicleDTO> ysfvs) {
        this.ysfvs = ysfvs;
    }
}
