package com.zhuanjingkj.zjcbe.business.dto.analysis;

import io.swagger.annotations.ApiModelProperty;

public class VideoUploadResponseDTO {

    @ApiModelProperty("推流Id")
    private int streamId;
    @ApiModelProperty("推流地址")
    private String rstpUrl;
    @ApiModelProperty("拉流地址")
    private String rtmpUrl;

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
}
