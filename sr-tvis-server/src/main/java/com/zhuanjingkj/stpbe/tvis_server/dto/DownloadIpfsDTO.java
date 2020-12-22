package com.zhuanjingkj.stpbe.tvis_server.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class DownloadIpfsDTO extends BaseDTO {
    @JSONField(name = "result")
    private int state;
    @JSONField(name = "dstFn")
    private String dstFn;
    @JSONField(name = "fileSize")
    private long fileSize;

    public DownloadIpfsDTO(int state, String dstFn, long fileSize) {
        this.state = state;
        this.dstFn = dstFn;
        this.fileSize = fileSize;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDstFn() {
        return dstFn;
    }

    public void setDstFn(String dstFn) {
        this.dstFn = dstFn;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
