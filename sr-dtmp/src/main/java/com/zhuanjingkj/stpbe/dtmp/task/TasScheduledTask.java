package com.zhuanjingkj.stpbe.dtmp.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TasScheduledTask {
    private static Logger logger = LoggerFactory.getLogger(TasScheduledTask.class);

    @Async("dtmpPool")
    @Scheduled(cron = "*/1 * * * * ?")
    public void runTasScheduledTask() {
        logger.info("TasScheduledTask.run ... " + System.currentTimeMillis() + "!");
    }
}