package com.zhuanjingkj.stpbe.data.rto.sm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class DeleteUserFromSmRTO extends BaseRTO {
    @JSONField(name ="userId")
    private Integer userId;

    public DeleteUserFromSmRTO() {
        super();
    }

    public DeleteUserFromSmRTO(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
