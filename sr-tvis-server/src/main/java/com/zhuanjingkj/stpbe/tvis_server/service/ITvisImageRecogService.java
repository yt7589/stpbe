package com.zhuanjingkj.stpbe.tvis_server.service;

import java.util.Map;

public interface ITvisImageRecogService {
    Map<String, Object> createLib(String name);

    Map<String, Object> queryLib(String id);

    Map<String, Object> updateLib(Map<String, Object> params);

    Map<String, Object> recognition(String cameraId, String gcxh, String mrhpt, String hphm, byte[] imageData);

    Map<String, Object> compareVehicle(String cltzxx1, String cltzxx2);

    Map<String, Object> compareInLib(String cltzxx, String kid, String xsdyz, String fydx, String ys);
}
