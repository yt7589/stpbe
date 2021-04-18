package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.ZjcBillSummaryDTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.ZjcBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *个人账单
 */
@RestController
@RequestMapping(value = "")
@CrossOrigin(value = "*")
public class ZjcBillController {

    @Autowired
    private ZjcBillService zjcBillService;


    /**
     * 账单列表
     * @param platform
     * @param version
     * @param startIndex
     * @param amount
     * @param direction
     * @param customerId
     * @return
     */
    @GetMapping(value = "/getBills")
    public ResultDTO<DbQrsDTO> getBills(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "startIndex", required = false) Integer startIndex,
            @RequestParam(name = "amount", required = false) Integer amount,
            @RequestParam(name = "direction", required = false) Integer direction,
            @RequestParam(name = "customerId", required = false) Integer customerId
    ) {
        return zjcBillService.getBills(startIndex, amount, direction, customerId);
    }

    /**
     * 账单总览
     * @param platform
     * @param version
     * @param customerId
     * @return
     */
    @GetMapping(value = "/getBillSummary")
    public ResultDTO<ZjcBillSummaryDTO> getBillSummary(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "customerId", required = false) Integer customerId
    ) {
        return zjcBillService.getBillSummary(customerId);
    }
}
