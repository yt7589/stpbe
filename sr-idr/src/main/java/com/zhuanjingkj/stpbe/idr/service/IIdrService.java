package com.zhuanjingkj.stpbe.idr.service;

import java.util.Map;

public interface IIdrService {
    Map<String, Object> recognition(String cameraId, String gcxh, String mrhpt, String hphm, byte[] imageData);
}
