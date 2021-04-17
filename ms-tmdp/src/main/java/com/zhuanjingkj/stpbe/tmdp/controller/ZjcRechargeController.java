package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.zjc.ZjcRechargeRTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.RechargeService;
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
    private RechargeService rechargeService;

    /**
     * 获取充值记录
     * @return
     */
    @GetMapping(value ="/getRechargeRecord")
    public ResultDTO<DbQrsDTO> getRechargeRecord(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            @RequestParam(name = "startIndex", required = false) Integer startIndex,
            @RequestParam(name = "amount", required = false) Integer amount,
            @RequestParam(name = "direction", required = false) Integer direction,
            @RequestParam(name = "customerId", required = false) Integer customerId,
            @RequestParam(name = "orderno", required = false) String orderno
    ) {
        return rechargeService.getRechargeRecord(customerId, startIndex, amount, direction, orderno);
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
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            @RequestBody ZjcRechargeRTO rto
    ) {
        return rechargeService.recharge(rto);
    }

}
