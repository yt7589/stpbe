package com.zhuanjingkj.zjcbe.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("vehicle_rtspconfig")
public class VehicleRtspconfigEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField("rstpId")
    private String rstpId;
    @TableField("rstpUrl")
    private String rstpUrl;
    @TableField("rtmpUrl")
    private String rtmpUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRstpId() {
        return rstpId;
    }

    public void setRstpId(String rstpId) {
        this.rstpId = rstpId;
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
