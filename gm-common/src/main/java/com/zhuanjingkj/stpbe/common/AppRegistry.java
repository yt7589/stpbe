package com.zhuanjingkj.stpbe.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AppRegistry {
    private static Map<String, Object> params = new HashMap<>();

    public static void putParam(String paramName, Object paramVal) {
        params.put(paramName, paramVal);
    }

    public static Optional<Object> getParam(String paramName) {
        if (params.containsKey(paramName)) {
            return Optional.of(params.get(paramName));
        } else {
            //return Optional.ofNullable(null);
            return Optional.empty();
        }
    }

    //
    public static String tvisJsonTblName = "";
    public static long tvisJsonTblRecs = 0;
}
