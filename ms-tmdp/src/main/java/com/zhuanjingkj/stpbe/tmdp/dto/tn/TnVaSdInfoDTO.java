package com.zhuanjingkj.stpbe.tmdp.dto.tn;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 点位设备信息
 */
public class TnVaSdInfoDTO extends BaseDTO {
    @JSONField(name = "diId")
    private String diId; //设备id
    @JSONField(name = "diAddr")
    private String diAddr; //设备位置
    @JSONField(name = "direction")
    private String direction; //设备方向
    @JSONField(name = "category")
    private String category; //设备类别
    @JSONField(name = "status")
    private String status; //设备状态

    public TnVaSdInfoDTO(String diId, String diAddr, String direction, String category, String status) {
        this.diId = diId;
        this.diAddr = diAddr;
        this.direction = direction;
        this.category = category;
        this.status = status;
    }

    public String getDiId() {
        return diId;
    }

    public void setDiId(String diId) {
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
}
