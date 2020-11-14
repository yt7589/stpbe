package com.zhuanjingkj.zjcbe.zjc_saas.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class AgzsService implements IAgzsService {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String callGetSystemVersion(String systemName) {
        return restTemplate.getForObject("http://ms-system-info/" +
                "systemInfo/getSystemVersion?systemName=" + systemName,
                String.class);
    }

    @HystrixCommand(fallbackMethod = "defaultCallGetContacts", commandProperties = {
            @HystrixProperty(
                    name="execution.isolation.strategy",
                    value="THREAD"
            ),
            @HystrixProperty(
                    name="execution.isolation.thread.timeoutInMilliseconds",
                    value="1000"
            )
    })
    @Override
    public String callGetContacts() {
        return restTemplate.getForObject("http://ms-system-info/" +
                "systemInfo/getContacts", String.class);
    }
    public String defaultCallGetContacts() {
        return "缺省响应001";
    }
}
