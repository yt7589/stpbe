package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.ZjcRechargeMapper;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.ZjcRechargeRecordDTO;
import com.zhuanjingkj.stpbe.data.rto.zjc.ZjcRechargeRTO;
import com.zhuanjingkj.stpbe.tmdp.service.IZjcRechargeService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ZjcRechargeService implements IZjcRechargeService {

    @Autowired
    private ZjcRechargeMapper zjcRechargeMapper;

    @Override
    public ResultDTO<DbQrsDTO> getRechargeRecord(String customer, Integer startIndex, Integer amount,
                                                 Integer direction,String orderno) {
        ResultDTO dto = new ResultDTO();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<ZjcRechargeRecordDTO> recs = zjcRechargeMapper.getRechargeRecord(customer, startIndex, amount, orderno);
        Integer count = zjcRechargeMapper.getRechargeCount(customer, orderno);
        DbQrsDTO data = new DbQrsDTO(count,recs.size(),startIndex,amount,direction,recs);
        dto.setData(data);
        return dto;
    }

    @Override
    @Transactional
    public ResultDTO<DbInsertResultDTO> recharge(ZjcRechargeRTO rto) {
        /**
         * 1.添加充值记录
         * 2.更新用户账户信息
         */
        ResultDTO dto = new ResultDTO();
        rto.setOrderNo(getOrderno());
        rto.setCreateTime(DateUtil.getLocalDateTime());
        Integer affectedRows = zjcRechargeMapper.recharge(rto);
//        rechargeMapper.uptCustomerBalance(rto.getCustomerId(), rto.getAmount());
        DbInsertResultDTO data = new DbInsertResultDTO(rto.getRdId(),affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbQrsDTO> getCustomers() {
        return zjcRechargeMapper.getCustomers();
    }

    private synchronized String getOrderno() {
        return  "zjkj" + System.currentTimeMillis();
    }
}
