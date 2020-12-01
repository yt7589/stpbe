package com.zhuanjingkj.stpbe.data.rto.vehicle;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

/**
 * author by guoqiang
 * date on 2020.12.01
 **/
public class MsgRTO extends BaseRTO {

    /**
     * 设备号
     */
    @JSONField(name = "cameraId")
    private String cameraId;

    /**
     * 数据结果
     */
    @JSONField(name = "result")
    private ResultRTO result;

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public ResultRTO getResult() {
        return result;
    }

    public void setResult(ResultRTO result) {
        this.result = result;
    }
}
