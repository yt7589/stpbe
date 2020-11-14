package com.zhuanjingkj.zjcbe.commondata.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class CreateVideoStreamDTO extends BaseDTO{
    @JSONField(name="videoStreamId")
    private long videoStreamId;

    public long getVideoStreamId() {
        return videoStreamId;
    }

    public void setVideoStreamId(long videoStreamId) {
        this.videoStreamId = videoStreamId;
    }
}
