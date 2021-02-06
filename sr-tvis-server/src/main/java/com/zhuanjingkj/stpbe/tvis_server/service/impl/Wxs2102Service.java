package com.zhuanjingkj.stpbe.tvis_server.service.impl;

import com.zhuanjingkj.stpbe.tvis_server.service.IWxs2102Service;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Wxs2102Service implements IWxs2102Service {
    /**
     * 货车车型识别
     * @param tp 图片的Basement4编码
     * @return
     */
    @Override
    public Map<String, Object> truckRecog(String tp) {
        return truckRecog_exp(tp);
    }












    private Map<String, Object> truckRecog_exp(String tp) {
        Map<String, Object> rst = new HashMap<>();
        rst.put("CODE", "1");
        rst.put("MSG", "");
        rst.put("CLLX", "211");
        return rst;
    }
}
