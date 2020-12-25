package com.zhuanjingkj.stpbe.tebs.scs.obs;

import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.tebs.scs.ITvisStpObserver;

/**
 * 车辆特征向量观察者，将车辆特征向量加上tvisJsonId、wztz一起保存到Milvus中，
 * 供以图搜车进行查询
 */
public class CltzxlObserver implements ITvisStpObserver {
    @Override
    public void notifyObserver(VehicleVo vo) {
        System.out.println("############ tvisJsonId=" + vo.getTvisJsonId() + "; vehsIdx=" + vo.getVehsIdx() + "! #########");
    }
}
