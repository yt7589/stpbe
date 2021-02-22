package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 点位设备信息
 */
public class TnVaSdInfoDTO extends BaseDTO {
    @JSONField(name = "diId")
    private long diId; //设备id
    @JSONField(name = "diAddr")
    private String diAddr; //设备位置
    @JSONField(name = "direction")
    private int direction; //设备方向
    @JSONField(name = "category")
    private int category; //设备类别
    @JSONField(name = "status")
    private int status; //设备状态
    @JSONField(name = "streamId")
    private long streamId;

    public TnVaSdInfoDTO(long diId, String diAddr, int direction, int category, int status, long streamId) {
        this.diId = diId;
        this.diAddr = diAddr;
        this.direction = direction;
        this.category = category;
        this.status = status;
        this.streamId = streamId;
    }

    public long getDiId() {
        return diId;
    }

    public void setDiId(long diId) {
        this.diId = diId;
    }

    public String getDiAddr() {
        return diAddr;
    }

    public void setDiAddr(String diAddr) {
        this.diAddr = diAddr;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStreamId() {
        return streamId;
    }

    public void setStreamId(long streamId) {
        this.streamId = streamId;
    }
}
