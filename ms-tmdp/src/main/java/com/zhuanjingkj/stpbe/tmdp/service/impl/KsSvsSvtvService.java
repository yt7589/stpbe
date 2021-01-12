package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.KsSvsSvtvMapper;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.data.dto.KsSvsSvtvDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsSvsSvtvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KsSvsSvtvService implements IKsSvsSvtvService {

    @Autowired
    private KsSvsSvtvMapper ksSvsSvtvMapper;

    @Autowired
    private TvisJsonMapper tvisJsonMapper;

    @Override
    public List<KsSvsSvtvDTO> getKsSvsSvtvDTOs_exp() {
        String tvisJsonTblName = tvisJsonMapper.getLatesTvisJsonTblName();;
        return ksSvsSvtvMapper.getKsSvsSvtv(tvisJsonTblName);
    }
}
