package com.zhuanjingkj.stpbe.tmdp.controller;

import com.alibaba.fastjson.JSON;
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
    public String queryVehicle(@RequestBody Map<String, Object> joReq) {
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
        return JSON.toJSONString(rst);
    }
}
