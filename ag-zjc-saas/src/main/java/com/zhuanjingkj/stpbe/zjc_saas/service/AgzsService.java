package com.zhuanjingkj.stpbe.zjc_saas.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

//    @HystrixCommand(fallbackMethod = "defaultCallGetContacts", commandProperties = {
//            @HystrixProperty(
//                    name="execution.isolation.strategy",
//                    value="THREAD"
//            ),
//            @HystrixProperty(
//                    name="execution.isolation.thread.timeoutInMilliseconds",
//                    value="1000"
//            )
//    })
    @Override
    public String callGetContacts() {
        return restTemplate.getForObject("http://ms-system-info/" +
                "systemInfo/getContacts", String.class);
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
        System.out.println("ag-zjc-saas.AgzsService.queryVehicle ...");
        return restTemplate.getForObject("http://ms-tmdp/" + "dc/cs/queryVehicle", String.class);
    }

    public String defaultCallGetContacts() {
        return "缺省响应001";
    }
}
