package com.zhuanjingkj.stpbe.tvis_server.service;

import com.alibaba.fastjson.JSONArray;

public interface IXaidrService {
    public JSONArray detectXaidrImage(byte[] imageData);
}
