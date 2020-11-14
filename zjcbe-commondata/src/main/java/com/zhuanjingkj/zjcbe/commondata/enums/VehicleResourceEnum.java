package com.zhuanjingkj.zjcbe.commondata.enums;

public enum VehicleResourceEnum {

    TEMP(1, "tempfiles", "临时文件"),
    IMAGE(2, "images", "图片文件"),
    SHOT(3, "shot", "视频流截图"),
    VIDEO(4, "videos", "视频文件");

    private int value;
    private String name;
    private String desp;

    VehicleResourceEnum(int _val, String _name, String _desp) {
        this.value = _val;
        this.name = _name;
        this.desp = _desp;
    }

    public static VehicleResourceEnum getEnum(int val) {
        for (VehicleResourceEnum state : VehicleResourceEnum.values()) {
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

    public String getDesp() {
        return this.desp;
    }
}
