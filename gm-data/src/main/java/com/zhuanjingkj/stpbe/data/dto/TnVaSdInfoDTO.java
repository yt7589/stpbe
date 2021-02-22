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
    private String direction; //设备方向
    @JSONField(name = "category")
    private String category; //设备类别
    @JSONField(name = "status")
    private String status; //设备状态
    @JSONField(name = "streamId")
    private int streamId;

    public TnVaSdInfoDTO(long diId, String diAddr, String direction, String category, String status, int streamId) {
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }
}
