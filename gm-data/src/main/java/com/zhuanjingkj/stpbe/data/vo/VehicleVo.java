package com.zhuanjingkj.stpbe.data.vo;

public class VehicleVo {
    private long tvisJsonId; // JSON的唯一编号
    private long vehsIdx; // VEH数组的序号
    private VehicleWztzVo vehicleWztzVo; // 位置特征
    private VehicleHptzVO vehicleHptzVO; // 号牌特征
    private VehicleCltzxlVo vehicleCltzxlVo; // 车辆特征向量
    private VehicleCxtzVo vehicleCxtzVo; // 车型特征

    public long getTvisJsonId() {
        return tvisJsonId;
    }

    public void setTvisJsonId(long tvisJsonId) {
        this.tvisJsonId = tvisJsonId;
    }

    public long getVehsIdx() {
        return vehsIdx;
    }

    public void setVehsIdx(long vehsIdx) {
        this.vehsIdx = vehsIdx;
    }

    public VehicleWztzVo getVehicleWztzVo() {
        return vehicleWztzVo;
    }

    public void setVehicleWztzVo(VehicleWztzVo vehicleWztzVo) {
        this.vehicleWztzVo = vehicleWztzVo;
    }

    public VehicleHptzVO getVehicleHptzVO() {
        return vehicleHptzVO;
    }

    public void setVehicleHptzVO(VehicleHptzVO vehicleHptzVO) {
        this.vehicleHptzVO = vehicleHptzVO;
    }

    public VehicleCltzxlVo getVehicleCltzxlVo() {
        return vehicleCltzxlVo;
    }

    public void setVehicleCltzxlVo(VehicleCltzxlVo vehicleCltzxlVo) {
        this.vehicleCltzxlVo = vehicleCltzxlVo;
    }

    public VehicleCxtzVo getVehicleCxtzVo() {
        return vehicleCxtzVo;
    }

    public void setVehicleCxtzVo(VehicleCxtzVo vehicleCxtzVo) {
        this.vehicleCxtzVo = vehicleCxtzVo;
    }
}
