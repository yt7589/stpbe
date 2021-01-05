package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkMainDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkTitfDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkTitfItemDTO;

import java.util.ArrayList;
import java.util.List;

public interface IDkTitfService {
    /**
     * 计算交通当天和昨天的交通流量
     * @return
     */
    public DkTitfDTO countTrafficFlow();

    public DkTitfDTO getDkTitfDTO_exp();

}
