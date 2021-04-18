package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.ZjcChargesMapper;
import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.data.rto.zjc.AddChargesRTO;
import com.zhuanjingkj.stpbe.data.rto.zjc.DeleteChargesRTO;
import com.zhuanjingkj.stpbe.tmdp.service.IZjcChargesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZjcChargesService implements IZjcChargesService {

    @Autowired
    private ZjcChargesMapper zjcChargesMapper;

    @Override
    public ResultDTO<DbQrsDTO> getCharges(Integer startIndex, Integer amount, Integer direction, Integer charges_id) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<ZjcChargesDTO> recs = zjcChargesMapper.getCharges(startIndex, amount, direction, charges_id);
        Integer count = zjcChargesMapper.getChargesCount(charges_id);
        DbQrsDTO data = new DbQrsDTO(count,recs.size(),startIndex,amount,direction,recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbInsertResultDTO> addCharges(AddChargesRTO rto) {
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<>();
        Integer affectedRows = zjcChargesMapper.addCharges(rto);
        DbInsertResultDTO data = new DbInsertResultDTO(rto.getChargeId(),affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> uptCharges(AddChargesRTO rto) {
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        Integer affectedRows = zjcChargesMapper.uptCharges(rto);
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> deleteCharges(DeleteChargesRTO rto) {
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        Integer affectedRows = zjcChargesMapper.deleteCharges(rto.getChargeId());
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }
}
