package com.zhuanjingkj.zjcbe.commondata.enums;

/**
 * @description: 通用业务状态枚举
 * @author: liuxiaogang.bj
 * @create: 2019/9/27 16:33
 **/
public enum CommonStatusEnum {
    UNVALID(-1, "无效"),
    NEW(0, "新建"),
    VALID(1, "有效"),
    DELETE(99, "逻辑删除");

    private int value;
    private String name;

    CommonStatusEnum(int _val, String _name) {
        this.value = _val;
        this.name = _name;
    }

    public static CommonStatusEnum getEnum(int val) {
        for (CommonStatusEnum state : CommonStatusEnum.values()) {
            if (state.getValue() == val) {
                return state;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return this.name;
    }

}
