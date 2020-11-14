package com.zhuanjingkj.zjcbe.zjc_saas.fcc;

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
        };
    }
}
