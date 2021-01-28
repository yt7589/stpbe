package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 抓拍机上传图片响应结果
 */
public class SubmitImageDTO {
    @JSONField(name = "tvisJsonId")
    private long tvisJsonId;

    public long getTvisJsonId() {
        return tvisJsonId;
    }

    public void setTvisJsonId(long tvisJsonId) {
        this.tvisJsonId = tvisJsonId;
    }
}
