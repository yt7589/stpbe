package com.zhuanjingkj.stpbe.data.vo;

public class VehicleVO {
    private long tvisJsonId; // JSON的唯一编号
    private long vehsIdx; // VEH数组的序号
    private long cameraId;
    private long streamId;
    private long trackId; // 用于进行物体跟踪
    private String occurTime; //时间
    private VehicleWztzVO vehicleWztzVo; // 位置特征
    private VehicleHptzVO vehicleHptzVO; // 号牌特征
    private VehicleCltzxlVO vehicleCltzxlVo; // 车辆特征向量
    private VehicleCxtzVO vehicleCxtzVo; // 车型特征
    private VehicleJsxwtzVO vehicleJsxwtzVO; //驾驶行为特征
    private VehicleGxhtzVO vehicleGxhtzVO; //个性化特征

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

    public long getCameraId() {
        return cameraId;
    }

    public void setCameraId(long cameraId) {
        this.cameraId = cameraId;
    }

    public long getStreamId() {
        return streamId;
    }

    public void setStreamId(long streamId) {
        this.streamId = streamId;
    }

    public long getTrackId() {
        return trackId;
    }

    public void setTrackId(long trackId) {
        this.trackId = trackId;
    }

    public VehicleWztzVO getVehicleWztzVo() {
        return vehicleWztzVo;
    }

    public void setVehicleWztzVo(VehicleWztzVO vehicleWztzVo) {
        this.vehicleWztzVo = vehicleWztzVo;
    }

    public VehicleHptzVO getVehicleHptzVO() {
        return vehicleHptzVO;
    }

    public void setVehicleHptzVO(VehicleHptzVO vehicleHptzVO) {
        this.vehicleHptzVO = vehicleHptzVO;
    }

    public VehicleCltzxlVO getVehicleCltzxlVo() {
        return vehicleCltzxlVo;
    }

    public void setVehicleCltzxlVo(VehicleCltzxlVO vehicleCltzxlVo) {
        this.vehicleCltzxlVo = vehicleCltzxlVo;
    }

    public VehicleCxtzVO getVehicleCxtzVo() {
        return vehicleCxtzVo;
    }

    public void setVehicleCxtzVo(VehicleCxtzVO vehicleCxtzVo) {
        this.vehicleCxtzVo = vehicleCxtzVo;
    }

    public VehicleJsxwtzVO getVehicleJsxwtzVO() {
        return vehicleJsxwtzVO;
    }

    public void setVehicleJsxwtzVO(VehicleJsxwtzVO vehicleJsxwtzVO) {
        this.vehicleJsxwtzVO = vehicleJsxwtzVO;
    }

    public VehicleGxhtzVO getVehicleGxhtzVO() {
        return vehicleGxhtzVO;
    }

    public void setVehicleGxhtzVO(VehicleGxhtzVO vehicleGxhtzVO) {
        this.vehicleGxhtzVO = vehicleGxhtzVO;
    }

    public String getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(String occurTime) {
        this.occurTime = occurTime;
    }
}
