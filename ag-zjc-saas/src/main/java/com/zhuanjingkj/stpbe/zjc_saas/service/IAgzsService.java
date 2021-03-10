package com.zhuanjingkj.stpbe.zjc_saas.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface IAgzsService {
    public String callGetSystemVersion(String systemName);
    public String callGetContacts();
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
            int amount);
}
