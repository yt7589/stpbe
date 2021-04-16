package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.ChargesMapper;
import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.data.rto.zjc.AddCharges;
import com.zhuanjingkj.stpbe.tmdp.service.IChargesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargesService implements IChargesService {

    @Autowired
    private ChargesMapper chargesMapper;

    @Override
    public ResultDTO<DbQrsDTO> getCharges(Integer startIndex, Integer amount, Integer direction, Integer charges_id) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<ZjcChargesDTO> recs = chargesMapper.getCharges(startIndex, amount, direction, charges_id);
        DbQrsDTO data = new DbQrsDTO(0,recs.size(),startIndex,amount,direction,recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbInsertResultDTO> addCharges(AddCharges rto) {
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<>();
        Integer affectedRows = chargesMapper.addCharges(rto);
        DbInsertResultDTO data = new DbInsertResultDTO(rto.getCharge_id(),affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> uptCharges(AddCharges rto) {
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        Integer affectedRows = chargesMapper.uptCharges(rto);
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }
}