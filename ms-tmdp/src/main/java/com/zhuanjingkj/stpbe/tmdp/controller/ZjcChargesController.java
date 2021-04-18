package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.zjc.AddChargesRTO;
import com.zhuanjingkj.stpbe.data.rto.zjc.DeleteChargesRTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.ZjcChargesService;
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
    private ZjcChargesService zjcChargesService;

    /**
     * 收费管理
     * @param platform
     * @param version
     * @param startIndex
     * @param amount
     * @param direction
     * @param chargesId
     * @return
     */
    @GetMapping(value = "/getCharges")
    public ResultDTO<DbQrsDTO> getCharges(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
            @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
            @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction,
            @RequestParam(name = "chargesId", required = false) Integer chargesId
     ) {
        return zjcChargesService.getCharges(startIndex, amount, direction, chargesId);
    }

    /**
     * 添加收费标准
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PostMapping(value = "/addCharges")
    public ResultDTO<DbInsertResultDTO> addCharges(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestBody AddChargesRTO rto
    ) {
        return zjcChargesService.addCharges(rto);
    }

    /**
     * 修改收费标准
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PutMapping(value = "/uptCharges")
    public ResultDTO<DbDeleteResultDTO> uptCharges(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestBody AddChargesRTO rto
    ) {
        return zjcChargesService.uptCharges(rto);
    }

    /**
     * 删除收费标准
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @DeleteMapping(value ="/deleteCharges")
    public ResultDTO<DbDeleteResultDTO> deleteCharges(
            @RequestParam(name = "p", required = false) String platform,
            @RequestParam(name = "v", required = false) String version,
            @RequestBody DeleteChargesRTO rto
    ) {
        return zjcChargesService.deleteCharges(rto);
    }
}
