package com.zhuanjingkj.zjcbe.data.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class UserVO implements Serializable {
    private static final long serialVersionUID = 5237730257103305078L;
    private Long userId;
    private String userName;
    private Integer roleId;
    private String roleName;

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
}
