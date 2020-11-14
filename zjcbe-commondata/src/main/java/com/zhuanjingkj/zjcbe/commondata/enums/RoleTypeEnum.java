package com.zhuanjingkj.zjcbe.commondata.enums;

public enum RoleTypeEnum {
    SUPER_ADMIN(100, 1, "系统管理员"),
    VISITOR(500, 2, "游客"),
    MANAGER(501, 3, "游客"),
    COMMON(502, 4, "普通员工");

    private int value;

    private int id;

    private String name;

    RoleTypeEnum(int _val, int _id, String _name) {
        this.value = _val;
        this.name = _name;
        this.id = _id;
    }

    public static RoleTypeEnum getEnum(int val) {
        for (RoleTypeEnum state : RoleTypeEnum.values()) {
            if (state.getValue() == val) {
                return state;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public int getRoleId() {
        return id;
    }

    public String getName() {
        return this.name;
    }
}
