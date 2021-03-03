package com.zhuanjingkj.stpbe.tvis_server.wxs2102.service;

import com.alibaba.fastjson.JSONArray;

import java.util.Map;

public interface ITsXaidrService {
    public JSONArray detectXaidrImage(byte[] imageData);
}
