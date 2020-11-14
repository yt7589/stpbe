package com.zhuanjingkj.zjcbe.commondata.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.zjcbe.commondata.po.VideoStreamPO;

import java.util.List;

public class GetVideoStreamsDTO extends BaseDTO {
    @JSONField(name="total")
    private int total;
    @JSONField(name="realNum")
    private int realNum;
    @JSONField(name="videoStreams")
    private List<VideoStreamPO> videoStreams;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRealNum() {
        return realNum;
    }

    public void setRealNum(int realNum) {
        this.realNum = realNum;
    }

    public List<VideoStreamPO> getVideoStreams() {
        return videoStreams;
    }

    public void setVideoStreams(List<VideoStreamPO> videoStreams) {
        this.videoStreams = videoStreams;
    }
}
