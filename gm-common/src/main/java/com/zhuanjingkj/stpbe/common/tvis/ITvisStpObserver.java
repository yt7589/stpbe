package com.zhuanjingkj.stpbe.common.tvis;

import com.zhuanjingkj.stpbe.data.vo.VehicleVO;
import org.springframework.core.env.Environment;

public interface ITvisStpObserver {
    public void notifyObserver(VehicleVO vo);
    public void initialize(Environment env);
}
