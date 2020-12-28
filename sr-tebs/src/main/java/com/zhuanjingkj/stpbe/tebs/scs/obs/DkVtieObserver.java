package com.zhuanjingkj.stpbe.tebs.scs.obs;

import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.data.vo.VehicleHptzVO;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.tebs.scs.ITvisStpObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 首页数据看板页面左侧第一行第一个报表，本地外埠车辆统计
 */
@Component
public class DkVtieObserver implements ITvisStpObserver {
    @Value("${hphm.native.prefix}")
    private String hphmNativePrefix;

    @Override
    public void notifyObserver(VehicleVo vo) {
        String hphm = vo.getVehicleHptzVO().getHphm();
        System.out.println("    ????? hphm=" + hphm + "; hphmNativePrefix=" + hphmNativePrefix + "!");
    }
}
