package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.ZjcConsumeMapper;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.ZjcConsumeRecordDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IZjcConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZjcConsumeService implements IZjcConsumeService {

    @Autowired
    private ZjcConsumeMapper zjcConsumeMapper;

    @Override
    public ResultDTO<DbQrsDTO> getConsumeRecord(Integer startIndex, Integer amount, Integer direction, Integer customerId) {
        ResultDTO dto = new ResultDTO();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<ZjcConsumeRecordDTO> recs = zjcConsumeMapper.getConsumeRecord(startIndex, amount, customerId);
        Integer count = zjcConsumeMapper.getConsumeCount(customerId);
        DbQrsDTO data = new DbQrsDTO(count,recs.size(),startIndex,amount,direction,recs);
        dto.setData(data);
        return dto;
    }
}
