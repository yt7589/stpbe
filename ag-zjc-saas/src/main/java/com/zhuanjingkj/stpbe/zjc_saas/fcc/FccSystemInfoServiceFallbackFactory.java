package com.zhuanjingkj.stpbe.zjc_saas.fcc;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FccSystemInfoServiceFallbackFactory implements FallbackFactory<FccSystemInfoService> {
    @Override
    public FccSystemInfoService create(Throwable throwable) {
        return new FccSystemInfoService() {
            @Override
            public String getSystemVersion(String systemName) {
                return "Hystrix: " + systemName + "; cause: " +
                        throwable.getMessage() + "; s=" + throwable.getCause() +"!";
            }
            @Override
            public String queryVehicle(
                    String platform,
                    String version,
                    String cltzxl,
                    String psfx,
                    String cllxfl,
                    String cllxzfl,
                    String startDate,
                    String endDate,
                    String startTime,
                    String endTime,
                    int startIndex,
                    int amount) {
                return "";
            }
        };
    }
}
