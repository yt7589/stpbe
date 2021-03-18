package com.zhuanjingkj.stpbe.tmdp.task;

import com.zhuanjingkj.stpbe.tmdp.service.impl.SmDcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RkcScheduledTask {

    @Autowired
    private SmDcService smDcService;

    /**
     * 0点清空本日数据
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void runTrkcScheduledTask() {
        smDcService.trkc();
    }

    /**
     * 1号清空当月数据
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void runMrkcScheduledTask() {
        smDcService.mrkc();
    }

    /**
     * 0点生成数据报告
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void runReportScheduledTask() {
        smDcService.report();
    }
}
