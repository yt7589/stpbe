package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcCsDTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.DcCsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Center =》 Car Search
 */
@RestController
@RequestMapping(value = "/dc")
@CrossOrigin(origins = "*")
public class DcCsController {
    @Autowired
    private DcCsService dcCsService;

    /**
     * 以图搜车
     * @param platform
     * @param version
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping(value = "/cs/queryVehicle")
    public ResultDTO<DbQrsDTO> queryVehicle(
        @RequestParam(name = "p") String platform,
        @RequestParam(name = "v") String version,
        @RequestParam(name = "cltzxl", required = true) String cltzxl,
        @RequestParam(name = "psfx", required = true) String psfx,
        @RequestParam(name = "cllxfl", required = true) String cllxfl,
        @RequestParam(name = "cllxzfl", required = true) String cllxzfl,
        @RequestParam(name = "startDate", required = false) String startDate,
        @RequestParam(name = "endDate", required = false) String endDate,
        @RequestParam(name = "startTime", required = false) String startTime,
        @RequestParam(name = "endTime", required = false) String endTime
    ) {
        System.out.println("车辆特征向量：" + cltzxl + "!");
        return dcCsService.queryVehicleByGraph(cltzxl, psfx, cllxfl, cllxzfl, startDate, endDate, startTime, endTime);
    }
}
