package com.zhuanjingkj.stpbe.tvis_server.service;

import java.util.Map;

public interface IWxs2102Service {
    public Map<String, Object> truckRecog(String tp);
    public Map<String, Object> carryPerson(String tp);
    public Map<String, Object> bigPlate(String tp);
    public Map<String, Object> motorClassify(String tp);
}
