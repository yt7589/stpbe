package com.zhuanjingkj.stpbe.tebs.scs.obs;

import com.zhuanjingkj.stpbe.data.vo.VehicleVo;
import com.zhuanjingkj.stpbe.tebs.scs.ITvisStpObserver;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DkVtpObserver implements ITvisStpObserver {
    @Override
    public void notifyObserver(VehicleVo vo) {
        
    }

    @Override
    public void initialize(Environment env) {

    }
}
