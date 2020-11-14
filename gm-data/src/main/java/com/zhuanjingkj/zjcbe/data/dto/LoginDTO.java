package com.zhuanjingkj.zjcbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class LoginDTO extends BaseDTO {
    @JSONField(name="userId")
    private Long userId;
    @JSONField(name="userName")
    private String userName;
    @JSONField(name = "roleId")
    private Integer roleId;
    @JSONField(name = "roleName")
    private String roleName;
    @JSONField(name = "jwtToken")
    private String jwtToken;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
