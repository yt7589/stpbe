package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbDeleteResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbInsertResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsVcLsvsDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsVcSfvsDTO;
import com.zhuanjingkj.stpbe.data.rto.ks.AddVehicleToVcRTO;
import com.zhuanjingkj.stpbe.data.rto.ks.DeleteVehicleFromVcRTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.KsVcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Key Supervision =>Vehicle control
 * 重点监管=》车辆布控
 */
@RestController
@RequestMapping(value = "/ks")
@CrossOrigin(origins = "*")
public class KsVcController {

    @Autowired
    private KsVcService ksVcService;

    /**
     * 布控车辆列表
     * @param platform
     * @param version
     * @param hphm  车牌号码
     * @param startIndex
     * @param amount
     * @param direction
     * @return
     */
    @GetMapping(value = "/vc/queryVehicle")
    public ResultDTO<DbQrsDTO> queryVehicle(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestParam(name = "hphm", required = false) String hphm,
        @RequestParam(name = "startIndex", required = false, defaultValue = "0") Integer startIndex,
        @RequestParam(name = "amount", required = false, defaultValue = "10") Integer amount,
        @RequestParam(name = "direction", required = false, defaultValue = "1") Integer direction
    ) {
        return queryVehicle_exp(hphm, startIndex, amount, direction);
    }

    /**
     * 添加布控车辆
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PostMapping(value = "/vc/addVehicle")
    public ResultDTO<DbInsertResultDTO> addVehicle(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestBody AddVehicleToVcRTO rto
    ) {
        return addVehicle_exp(rto);
    }

    /**
     * 删除布控车辆
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @DeleteMapping(value = "/vc/deleteVehicle")
    public ResultDTO<DbDeleteResultDTO> deleteVehicle(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestBody DeleteVehicleFromVcRTO rto
    ) {
        return deleteVehicle_exp(rto);
    }

    /**
     * 布控车辆地图点位
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/vc/queryVcSfvs")
    public ResultDTO<DbQrsDTO> queryVcSfvs(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        return queryVcSfvs_exp();
    }

    /**
     * 车辆布控最新动态
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/vc/queryVcLsvs")
    public ResultDTO<DbQrsDTO> queryVcLsvs(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        return queryVcDynLsvs_exp();
    }

    /**
     * 车辆布控最新违章报警
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/vc/queryVcLtvis")
    public ResultDTO<DbQrsDTO> queryVcLtvis(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        return queryVcIllLsvs_exp();
    }

    private ResultDTO<DbQrsDTO> queryVehicle_exp(String hphm, Integer startIndex, Integer amount, Integer direction) {
        return ksVcService.queryVehicle_exp(hphm, startIndex, amount, direction);
    }

    private ResultDTO<DbInsertResultDTO> addVehicle_exp(AddVehicleToVcRTO rto) {
        return  ksVcService.addVehicle_exp(rto);
    }

    private ResultDTO<DbDeleteResultDTO> deleteVehicle_exp(DeleteVehicleFromVcRTO rto) {
        return ksVcService.deleteVehicle_exp(rto);
    }

    private ResultDTO<DbQrsDTO> queryVcSfvs_exp() {
        return ksVcService.queryVcSfvs_exp();
    }

    private ResultDTO<DbQrsDTO> queryVcDynLsvs_exp() {
        return ksVcService.queryVcDynLsvs_exp();
    }

    private ResultDTO<DbQrsDTO> queryVcIllLsvs_exp() {
        return ksVcService.queryVcIllLsvs_exp();
    }
}

