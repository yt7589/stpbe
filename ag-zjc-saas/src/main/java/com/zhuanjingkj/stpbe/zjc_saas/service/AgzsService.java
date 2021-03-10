package com.zhuanjingkj.stpbe.zjc_saas.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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

    @HystrixCommand(fallbackMethod = "defaultQueryVehicle", commandProperties = {
            @HystrixProperty(
                    name="execution.isolation.strategy",
                    value="THREAD"
           ),
            @HystrixProperty(
                    name="execution.isolation.thread.timeoutInMilliseconds",
                    value="100000"
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
        Map<String, Object> params = new HashMap<>();
        params.put("p", platform);
        params.put("v", version);
        params.put("cltzxl", cltzxl);
        params.put("psfx", psfx);
        params.put("cllxfx", cllxfl);
        params.put("cllxzfl", cllxzfl);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("startIndex", startIndex);
        params.put("amount", amount);
        StringBuilder cmd = new StringBuilder("dc/cs/queryVehicle");
        /*cmd.append("?p=" + platform + "&");
        cmd.append("v=" + version + "&");
        cmd.append("cltzxl=" + cltzxl + "&");
        cmd.append("psfx=" + psfx + "&");
        cmd.append("cllxfl=" + cllxfl + "&");
        cmd.append("cllxzfl=" + cllxzfl + "&");
        cmd.append("startIndex=" + startIndex + "&");
        cmd.append("amount=" + amount);*/
        String agResp = restTemplate.postForObject("http://ms-tmdp/" + cmd.toString(), params, String.class);
        System.out.println("agService Resp:" + agResp + "!");
        return agResp;
    }
    public String defaultQueryVehicle(
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
}
