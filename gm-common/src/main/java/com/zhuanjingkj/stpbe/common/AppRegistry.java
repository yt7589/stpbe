package com.zhuanjingkj.stpbe.common;

import com.zhuanjingkj.stpbe.data.vo.VehicleVO;

import java.util.*;

public class AppRegistry {
    private static Map<String, Object> params = new HashMap<>();
    // 保存从视频帧中解析出来的车辆值对象列表，供ObserverThread读取，存入系统数据库中
    public static Queue<VehicleVO> vehicleVOS = new LinkedList<>();

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
