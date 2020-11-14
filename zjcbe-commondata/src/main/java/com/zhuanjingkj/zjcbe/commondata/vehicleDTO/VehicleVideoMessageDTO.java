package com.zhuanjingkj.zjcbe.commondata.vehicleDTO;

public class VehicleVideoMessageDTO {
    private int streamId;
    private String rstpUrl;
    private String rtmpUrl;
    private String videoPath;

    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }

    public String getRstpUrl() {
        return rstpUrl;
    }

    public void setRstpUrl(String rstpUrl) {
        this.rstpUrl = rstpUrl;
    }

    public String getRtmpUrl() {
        return rtmpUrl;
    }

    public void setRtmpUrl(String rtmpUrl) {
        this.rtmpUrl = rtmpUrl;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
