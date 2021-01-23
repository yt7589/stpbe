package com.zhuanjingkj.stpbe.tmdp;

import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.tmdp.task.VideoAnalysisTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

public class MsTmdpApplicationEventListener implements ApplicationListener {
    @Autowired
    private TvisJsonMapper tvisJsonMapper;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("########### onApplication Event #############");
        if ( applicationEvent instanceof ApplicationReadyEvent ||
                applicationEvent instanceof ContextStartedEvent) {
            System.out.println("        create thread...");
            Thread thd = new Thread(new VideoAnalysisTask(tvisJsonMapper));
            thd.start();
        }
    }
}
