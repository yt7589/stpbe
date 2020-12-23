package com.zhuanjingkj.stpbe.tebs.scs.obs;

import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.tebs.scs.ITvisStpObserver;

public class DkVtieObserver implements ITvisStpObserver {
    @Override
    public void notifyObserver(VehicleVo vo) {
        System.out.println("    ##### 调用DkVtieObserver");
    }
}
