package com.zhuanjingkj.zjcbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class GetUserInfoDTO extends BaseDTO {
    @JSONField(name = "userId")
    private long userId;
    @JSONField(name = "userName")
    private String userName;
    @JSONField(name = "roleId")
    private int roleId;
    @JSONField(name = "roleName")
    private String roleName;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
