package com.zhuanjingkj.stpbe.tmdp.dto.dc;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 数据中心 =》 以图搜车
 */
public class DcCsDTO extends BaseDTO {
    @JSONField(name = "picId")
    private long picId; //照片id
    @JSONField(name = "picAddr")
    private String picAddr; //照片拍摄点位
    @JSONField(name = "picTime")
    private String picTime; //照片拍摄时间
    @JSONField(name = "picUrl")
    private String picUrl; //照片存储地址

    public DcCsDTO(long picId, String picAddr, String picTime, String picUrl) {
        this.picId = picId;
        this.picAddr = picAddr;
        this.picTime = picTime;
        this.picUrl = picUrl;
    }

    public long getPicId() {
        return picId;
    }

    public void setPicId(long picId) {
        this.picId = picId;
    }

    public String getPicAddr() {
        return picAddr;
    }

    public void setPicAddr(String picAddr) {
        this.picAddr = picAddr;
    }

    public String getPicTime() {
        return picTime;
    }

    public void setPicTime(String picTime) {
        this.picTime = picTime;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
