package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.zjc.ZjcRechargeRTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.ZjcRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 充值记录
 */
@RestController
@RequestMapping(value = "/zjc/recharge/")
@CrossOrigin(origins = "*")
public class ZjcRechargeController {

    @Autowired
    private ZjcRechargeService zjcRechargeService;

    /**
     * 获取充值记录
     * @return
     */
    @GetMapping(value ="/getRechargeRecord")
    public ResultDTO<DbQrsDTO> getRechargeRecord(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "startIndex", required = false) Integer startIndex,
            @RequestParam(name = "amount", required = false) Integer amount,
            @RequestParam(name = "direction", required = false) Integer direction,
            @RequestParam(name = "customer", required = false) String customer,
            @RequestParam(name = "orderno", required = false) String orderno
    ) {
        return zjcRechargeService.getRechargeRecord(customer, startIndex, amount, direction, orderno);
    }

    /**
     * 充值
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PostMapping(value = "/recharge")
    public ResultDTO<DbInsertResultDTO> recharge(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestBody ZjcRechargeRTO rto
    ) {
        return zjcRechargeService.recharge(rto);
    }

    public ResultDTO<DbQrsDTO> getCustomers(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version
    ) {
        return zjcRechargeService.getCustomers();
    }
}
