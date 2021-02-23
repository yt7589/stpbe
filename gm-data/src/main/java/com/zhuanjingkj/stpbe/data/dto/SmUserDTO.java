package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class SmUserDTO extends BaseDTO{
    @JSONField(name = "userId")
    private long userId;
    @JSONField(name = "roleId")
    private Integer roleId;
    @JSONField(name = "userName")
    private String userName;
    @JSONField(name = "loginName")
    private String loginName;
    @JSONField(name = "loginPwd")
    private String loginPwd;
    @JSONField(name = "phone")
    private String phone;
    @JSONField(name = "tright")
    private String tright;

    public SmUserDTO() {
        super();
    }

    public SmUserDTO(long userId, Integer roleId, String userName, String loginName,
                     String loginPwd, String phone, String tright) {
        this.userId = userId;
        this.roleId = roleId;
        this.userName = userName;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.phone = phone;
        this.tright = tright;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTright() {
        return tright;
    }

    public void setTright(String tright) {
        this.tright = tright;
    }
}
