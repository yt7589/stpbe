package com.zhuanjingkj.stpbe.tvis_server.wxs2102.service;

import com.alibaba.fastjson.JSONArray;

import java.util.Map;

public interface ITsWxs2102Service {
    public Map<String, Object> truckRecog(String tp);
    public Map<String, Object> carryPerson(String tp);
    public Map<String, Object> bigPlate(String tp);
    public Map<String, Object> motorClassify(String tp);
}
