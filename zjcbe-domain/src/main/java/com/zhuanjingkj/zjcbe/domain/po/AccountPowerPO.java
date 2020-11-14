package com.zhuanjingkj.zjcbe.domain.po;

public class AccountPowerPO {

    private int accountId;
    private int powerId;
    private String powerName;
    private String powerPath;
    private int powerType;
    private String icon;
    private int parentId;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int powerId) {
        this.powerId = powerId;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getPowerPath() {
        return powerPath;
    }

    public void setPowerPath(String powerPath) {
        this.powerPath = powerPath;
    }

    public int getPowerType() {
        return powerType;
    }

    public void setPowerType(int powerType) {
        this.powerType = powerType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
