package com.zhuanjingkj.zjcbe.zjc_saas.fcc;

import com.zhuanjingkj.zjcbe.zjc_saas.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="ms-system-info", path="/systemInfo",
        configuration = FeignConfiguration.class,
        fallbackFactory = FccSystemInfoServiceFallbackFactory.class
)
public interface FccSystemInfoService {
    @GetMapping("/getSystemVersion")
    public String getSystemVersion(@RequestParam(name="systemName") String systemName);
}
