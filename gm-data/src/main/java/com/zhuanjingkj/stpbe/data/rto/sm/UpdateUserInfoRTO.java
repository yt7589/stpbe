package com.zhuanjingkj.stpbe.data.rto.sm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class UpdateUserInfoRTO extends BaseRTO {
    @JSONField(name = "userId")
    private long userId;
    @JSONField(name = "loginName")
    private String loginName;
    @JSONField(name = "userName")
    private String userName;
    @JSONField(name = "phone")
    private String phone;
    @JSONField(name = "pwd")
    private String pwd;
    @JSONField(name = "roleId")
    private String roleId;

    public UpdateUserInfoRTO(long userId, String loginName, String userName, String phone, String pwd, String roleId) {
        this.userId = userId;
        this.loginName = loginName;
        this.userName = userName;
        this.phone = phone;
        this.pwd = pwd;
        this.roleId = roleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
