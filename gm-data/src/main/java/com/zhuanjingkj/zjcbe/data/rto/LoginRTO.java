package com.zhuanjingkj.zjcbe.data.rto;

import com.alibaba.fastjson.annotation.JSONField;

public class LoginRTO extends BaseRTO {
    @JSONField(name = "loginName")
    private String loginName;
    @JSONField(name = "loginPwd")
    private String loginPwd;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
}
