package com.zhuanjingkj.stpbe.data.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回给客户端的WebSocket消息内容
 */
public class WsmVideoFrameDTO extends BaseDTO {
    private long tvisJsonId;
    private long pts;
    private String originImage;
    private List<WsmVideoFrameVehicleDTO> data;
    private int trafficViolationIdx = -1;

    public WsmVideoFrameDTO(long tvisJsonId, long pts, String originImage) {
        this.tvisJsonId = tvisJsonId;
        this.pts = pts;
        this.originImage = originImage;
        this.data = new ArrayList<>();
    }

    public long getTvisJsonId() {
        return tvisJsonId;
    }

    public void setTvisJsonId(long tvisJsonId) {
        this.tvisJsonId = tvisJsonId;
    }

    public long getPts() {
        return pts;
    }

    public void setPts(long pts) {
        this.pts = pts;
    }

    public String getOriginImage() {
        return originImage;
    }

    public void setOriginImage(String originImage) {
        this.originImage = originImage;
    }

    public List<WsmVideoFrameVehicleDTO> getData() {
        return data;
    }

    public void setData(List<WsmVideoFrameVehicleDTO> data) {
        this.data = data;
    }

    public int getTrafficViolationIdx() {
        return trafficViolationIdx;
    }

    public void setTrafficViolationIdx(int trafficViolationIdx) {
        this.trafficViolationIdx = trafficViolationIdx;
    }
}
