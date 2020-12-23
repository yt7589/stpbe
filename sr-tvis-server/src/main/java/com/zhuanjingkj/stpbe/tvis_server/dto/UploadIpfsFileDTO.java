package com.zhuanjingkj.stpbe.tvis_server.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class UploadIpfsFileDTO extends BaseDTO {
    @JSONField(name = "fileHash")
    private String fileHash;

    public UploadIpfsFileDTO(String fileHash) {
        this.fileHash = fileHash;
    }

    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }
}
