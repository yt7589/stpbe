package com.zhuanjingkj.zjcbe.commondata.constance;

import java.util.HashMap;
import java.util.Map;

public class AppRegistry {
    private final static Map<String, Object> vars = new HashMap<>();

    public static void putVar(String key, Object obj) {
        vars.put(key, obj);
    }

    public static Object getVar(String key) {
        return vars.get(key);
    }
}
