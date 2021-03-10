package com.zhuanjingkj.stpbe.zjc_saas.fcc;

import com.zhuanjingkj.stpbe.zjc_saas.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="ms-system-info", path="/systemInfo",
        configuration = FeignConfiguration.class,
        fallbackFactory = FccSystemInfoServiceFallbackFactory.class
)
public interface FccSystemInfoService {
    @GetMapping("/getSystemVersion")
    public String getSystemVersion(@RequestParam(name="systemName") String systemName);
    @PostMapping(value = "/queryVehicle")
    public String queryVehicle(
            @RequestParam(name="p") String platform,
            @RequestParam(name="v") String version,
            @RequestParam(name = "cltzxl", required = true) String cltzxl,
            @RequestParam(name = "psfx", required = true) String psfx,
            @RequestParam(name = "cllxfl", required = true) String cllxfl,
            @RequestParam(name = "cllxzfl", required = true) String cllxzfl,
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "startTime", required = false) String startTime,
            @RequestParam(name = "endTime", required = false) String endTime,
            @RequestParam(name = "startIndex", required = false) int startIndex,
            @RequestParam(name = "amount", required = false) int amount);
}
