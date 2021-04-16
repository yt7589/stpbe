package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.zjc.AddCharges;
import com.zhuanjingkj.stpbe.tmdp.service.impl.ChargesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 收费管理
 */
@RestController
@RequestMapping(value = "/zjc/charges/")
@CrossOrigin(origins = "*")
public class ZjcChargesController {

    @Autowired
    private ChargesService chargesService;

    @GetMapping(value = "/getCharges")
    public ResultDTO<DbQrsDTO> getCharges(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
            @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
            @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction,
            @RequestParam(name = "chargesId", required = false) Integer chargesId
     ) {
        return chargesService.getCharges(startIndex, amount, direction, chargesId);
    }

    @PostMapping(value = "/addCharges")
    public ResultDTO<DbInsertResultDTO> addCharges(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestBody AddCharges rto
    ) {
        return chargesService.addCharges(rto);
    }

    @PutMapping(value = "/uptCharges")
    public ResultDTO<DbDeleteResultDTO> uptCharges(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestBody AddCharges rto
    ) {
        return chargesService.uptCharges(rto);
    }
}
