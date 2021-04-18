package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.ZjcConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 消费记录
 */
@RestController
@RequestMapping(value = "/zjc/consume/")
@CrossOrigin(origins = "*")
public class ZjcConsumeController {

    @Autowired
    private ZjcConsumeService zjcConsumeService;

    /**
     * 消费记录
     * @param platform
     * @param version
     * @param startIndex
     * @param amount
     * @param direction
     * @param customerId
     * @return
     */
    @GetMapping(value ="/getConsumerRecord")
    public ResultDTO<DbQrsDTO> getConsumeRecord(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            @RequestParam(name = "startIndex") Integer startIndex,
            @RequestParam(name = "amount") Integer amount,
            @RequestParam(name = "direction") Integer direction,
            @RequestParam(name = "customerId") Integer customerId
    ) {
        return zjcConsumeService.getConsumeRecord(startIndex, amount, direction, customerId);
    }

}
