package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DkMvtsMapper;
import com.zhuanjingkj.stpbe.data.dto.DkMvtsDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDkMvtsService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DkMvtsService implements IDkMvtsService {

    @Autowired
    private DkMvtsMapper dkMvtsMapper;

    @Override
    public List<DkMvtsDTO> getDkMvtsDTOs_exp() {
        System.out.println(DateUtil.getMonthOfYear());
        return dkMvtsMapper.getMvtss(DateUtil.getMonthOfYear());
    }
}
