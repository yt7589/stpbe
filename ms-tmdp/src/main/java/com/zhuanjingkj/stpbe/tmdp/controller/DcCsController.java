package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcCsDTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.DcCsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * @return
     */
    @PostMapping(value = "/cs/queryVehicle")
    public String queryVehicle(@RequestBody Map<String, Object> joReq
    /*public String queryVehicle(
        @RequestParam(name = "p") String platform,
                               @RequestParam(name = "v") String version,
                               @RequestParam(name = "cltzxl", required = true) String cltzxl,
                               @RequestParam(name = "psfx", required = true) String psfx,
                               @RequestParam(name = "cllxfl", required = true) String cllxfl,
                               @RequestParam(name = "cllxzfl", required = true) String cllxzfl,
                               @RequestParam(name = "startDate", required = false) String startDate,
                               @RequestParam(name = "endDate", required = false) String endDate,
                               @RequestParam(name = "startTime", required = false) String startTime,
                               @RequestParam(name = "endTime", required = false) String endTime,
                               @RequestParam(name = "startIndex", required = false) int startIndex,
                               @RequestParam(name = "amount", required = false) int amount*/
    ) {
        long t1 = System.currentTimeMillis();
        System.out.println("joReq:" + joReq.toString() + "!");
        String platform = (String)joReq.get("p");
        String version = (String)joReq.get("v");
        String cltzxl = (String)joReq.get("cltzxl");
        String psfx = (String)joReq.get("psfx");
        String cllxfl = (String)joReq.get("cllxfl");
        String cllxzfl = (String)joReq.get("cllxzfl");
        String startDate = "";
        String endDate = "";
        String startTime = "";
        String endTime = "";
        int startIndex = (int)joReq.get("startIndex");
        int amount = (int)joReq.get("amount");
        System.out.println("ms-tmdp::DcCsController.queryVehicle 1");
        ResultDTO<DbQrsDTO> rst = dcCsService.queryVehicleByGraph(cltzxl, psfx, cllxfl, cllxzfl, startDate,
                endDate, startTime, endTime, startIndex, amount);
        long diff = System.currentTimeMillis() - t1;
        System.out.println("runtime: " + diff + "!");
        return rst.toString();
    }
}
