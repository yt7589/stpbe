package com.zhuanjingkj.stpbe.tebs.rto;

import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class TvisJsonRTO extends BaseRTO {
    private String requestId;
    private String tvisJson;
    private String createTime;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTvisJson() {
        return tvisJson;
    }

    public void setTvisJson(String tvisJson) {
        this.tvisJson = tvisJson;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
