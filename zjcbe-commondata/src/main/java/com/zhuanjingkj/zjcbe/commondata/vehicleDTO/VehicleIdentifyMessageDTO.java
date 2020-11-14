package com.zhuanjingkj.zjcbe.commondata.vehicleDTO;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class VehicleIdentifyMessageDTO {

    @JSONField(name = "JLS")
    private int jls;
    @JSONField(name = "CODE")
    private int code;
    @JSONField(name = "VEH")
    private List<VehicleIdentifyDetailDTO> veh;
    @JSONField(name = "GCXH")
    private String gcxh;
    @JSONField(name = "TimeStamp")
    private String timeStamp;
    @JSONField(name = "StreamID")
    private String streamId;

    public int getJls() {
        return jls;
    }

    public void setJls(int jls) {
        this.jls = jls;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<VehicleIdentifyDetailDTO> getVeh() {
        return veh;
    }

    public void setVeh(List<VehicleIdentifyDetailDTO> veh) {
        this.veh = veh;
    }

    public String getGcxh() {
        return gcxh;
    }

    public void setGcxh(String gcxh) {
        this.gcxh = gcxh;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }
}
