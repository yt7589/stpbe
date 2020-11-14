package com.zhuanjingkj.zjcbe.commondata.vehicleDTO;

import com.alibaba.fastjson.annotation.JSONField;

public class VehicleImageMessageDTO {
    @JSONField(name = "timeStamp")
    private String timeStamp;
    @JSONField(name = "streamid")
    private String streamId;
    @JSONField(name = "sxh")
    private int sxh;
    @JSONField(name = "image")
    private String imageByteStr;

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

    public int getSxh() {
        return sxh;
    }

    public void setSxh(int sxh) {
        this.sxh = sxh;
    }

    public String getImageByteStr() {
        return imageByteStr;
    }

    public void setImageByteStr(String imageByteStr) {
        this.imageByteStr = imageByteStr;
    }
}
