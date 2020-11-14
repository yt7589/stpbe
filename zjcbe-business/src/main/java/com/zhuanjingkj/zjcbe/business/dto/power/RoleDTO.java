package com.zhuanjingkj.zjcbe.business.dto.power;

import io.swagger.annotations.ApiModelProperty;

public class RoleDTO {

    @ApiModelProperty("用户角色Id")
    private int roleId;
    @ApiModelProperty("用户角色编码")
    private int roleCode;
    @ApiModelProperty("用户角色名称")
    private String roleName;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(int roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
