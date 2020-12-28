package com.zhuanjingkj.stpbe.tebs.scs.obs;

import com.zhuanjingkj.stpbe.data.vo.VehicleHptzVO;
import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.tebs.scs.ITvisStpObserver;

/**
 * 首页数据看板页面左侧第一行第一个报表，本地外埠车辆统计
 */
public class DkVtieObserver implements ITvisStpObserver {
    @Override
    public void notifyObserver(VehicleVo vo) {
        VehicleHptzVO hptzVO = vo.getVehicleHptzVO();
        System.out.println("    ##### 调用DkVtieObserver: hphm=" + hptzVO.getHphm() + "!!!!!!!!!!!");
    }
}
