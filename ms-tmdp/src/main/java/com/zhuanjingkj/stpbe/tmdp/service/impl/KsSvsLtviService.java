package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.KsSvsLtviMapper;
import com.zhuanjingkj.stpbe.data.dto.KsSvsLtviDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsSvsLtviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KsSvsLtviService implements IKsSvsLtviService {

    @Autowired
    private KsSvsLtviMapper ksSvsLtviMapper;

    @Override
    public KsSvsLtviDTO getKsSvsLtviDTO_exp() {
        return ksSvsLtviMapper.getKsSvsLtvi();
    }
}
