package com.zhuanjingkj.stpbe.tmdp.task;

import com.zhuanjingkj.stpbe.tmdp.service.impl.SmDcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RkcScheduledTask {

    @Autowired
    private SmDcService smDcService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void runTrkcScheduledTask() {
        smDcService.trkc();
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    public void runMrkcScheduledTask() {
        smDcService.mrkc();
    }
}
