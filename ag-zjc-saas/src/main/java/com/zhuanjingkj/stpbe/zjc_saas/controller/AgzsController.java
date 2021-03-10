package com.zhuanjingkj.stpbe.zjc_saas.controller;

import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.zjc_saas.service.AgzsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agZjcSaas")
public class AgzsController {
    @Autowired
    AgzsService agzsService;
    //@Autowired
    //FccSystemInfoService fccSystemInfoService;

    @GetMapping("/callGetSystemVersion")
    public String callGetSystemVersion(@RequestParam(name="systemName") String systemName) {
        return "temp";
        //return "Feign: " + fccSystemInfoService.getSystemVersion(systemName);
        //return agzsService.callGetSystemVersion(systemName);
    }

    @GetMapping("/callGetContacts")
    public String callGetContacts() {
        return agzsService.callGetContacts();
    }



    /**
     * 以图搜车
     * @param platform
     * @param version
     * @param startTime
     * @param endTime
     * @return
     */
    @PostMapping(value = "/queryVehicle")
    public String queryVehicle(
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
            @RequestParam(name = "amount", required = false) int amount
    ) {
        System.out.println("ag-zjc-saas.AgzsController.queryVehicle ...");
        return agzsService.queryVehicle(platform,
                version,
                cltzxl,
                psfx,
                cllxfl,
                cllxzfl,
                startDate,
                endDate,
                startTime,
                endTime,
        startIndex,
        amount);
    }
}
