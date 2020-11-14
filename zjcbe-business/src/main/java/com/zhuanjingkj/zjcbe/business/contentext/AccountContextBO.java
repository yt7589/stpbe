package com.zhuanjingkj.zjcbe.business.contentext;

/**
 * @description: 用户权限上下文
 * @author: liuxiaogang.bj
 * @create: 2019/8/7 14:36
 **/
public class AccountContextBO {

    /**
     * 账户Id
     */
    private int accountId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 账户角色编号
     */
    private int roleCode;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(int roleCode) {
        this.roleCode = roleCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
