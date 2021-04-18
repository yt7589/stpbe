package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.ZjcBillMapper;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.ZjcBillDTO;
import com.zhuanjingkj.stpbe.data.dto.ZjcBillSummaryDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IZjcBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZjcBillService implements IZjcBillService {

    @Autowired
    private ZjcBillMapper zjcBillMapper;

    @Override
    public ResultDTO<DbQrsDTO> getBills(Integer startIndex, Integer amount, Integer direction, Integer customerId) {
        ResultDTO dto = new ResultDTO();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<ZjcBillDTO> recs = zjcBillMapper.getBills(startIndex, amount, customerId);
        Integer count = zjcBillMapper.getBillCount(customerId);
        DbQrsDTO data = new DbQrsDTO(count,recs.size(),startIndex,amount,direction,recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<ZjcBillSummaryDTO> getBillSummary(Integer customerId) {
        ResultDTO dto = new ResultDTO();
        Double totalRecharge = zjcBillMapper.getTotalRechargeByCustomerId(customerId); //总充值金额
        Double balance = 0.0;
        if(customerId > 1) {
            balance = zjcBillMapper.getBalanceByCustomerId(customerId); //余额
        }
        Integer picNum = zjcBillMapper.getPicCountByCustomerId(customerId); //检测图片数量
        Integer duration = zjcBillMapper.getVideoDurationByCustomerId(customerId); //检测视频时长
        ZjcBillSummaryDTO zjcBillSummaryDTO = new ZjcBillSummaryDTO(totalRecharge, balance, picNum, duration);
        dto.setData(zjcBillSummaryDTO);
        return dto;
    }

}
