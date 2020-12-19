package com.zhuanjingkj.stpbe.tmdp.task;

import com.zhuanjingkj.stpbe.tmdp.controller.TmdpWsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TmdpScheduledTask {
    @Autowired
    private TmdpWsHandler tmdpWsHandler;

    @Async("tmdpPool")
    @Scheduled(cron = "*/3 * * * * ?")
    public void runTasScheduledTask() {
        System.out.println("发送WebSocket推送信息......");
        tmdpWsHandler.pushLtvis("Hello World");
    }
}
