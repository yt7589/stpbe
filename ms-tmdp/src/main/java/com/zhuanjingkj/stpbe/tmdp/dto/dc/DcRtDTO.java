package com.zhuanjingkj.stpbe.tmdp.dto.dc;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class DcRtDTO extends BaseDTO {
    List<DcRtTimeJamDTO> rtj; //分时段拥堵趋势
    List<DcRtAreaJamDTO> raj; //分区高峰时段拥堵排名
    List<DcRtTimeVehicleDTO> rtv; //分时段过车量
    List<DcRtAreaVehicleDTO> rav; //分区过车量排名
    List<DcRtRoadJamDTO> rrj; //高峰时段拥堵路名排名

    public List<DcRtTimeJamDTO> getRtj() {
        return rtj;
    }

    public void setRtj(List<DcRtTimeJamDTO> rtj) {
        this.rtj = rtj;
    }

    public List<DcRtAreaJamDTO> getRaj() {
        return raj;
    }

    public void setRaj(List<DcRtAreaJamDTO> raj) {
        this.raj = raj;
    }

    public List<DcRtTimeVehicleDTO> getRtv() {
        return rtv;
    }

    public void setRtv(List<DcRtTimeVehicleDTO> rtv) {
        this.rtv = rtv;
    }

    public List<DcRtAreaVehicleDTO> getRav() {
        return rav;
    }

    public void setRav(List<DcRtAreaVehicleDTO> rav) {
        this.rav = rav;
    }

    public List<DcRtRoadJamDTO> getRrj() {
        return rrj;
    }

    public void setRrj(List<DcRtRoadJamDTO> rrj) {
        this.rrj = rrj;
    }
}
