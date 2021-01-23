package com.zhuanjingkj.stpbe.tmdp;

import com.zhuanjingkj.stpbe.tmdp.task.VideoAnalysisTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MsTmdpApplicationEventListener implements ApplicationListener {
    @Autowired
    VideoAnalysisTask videoAnalysisTask;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if ( applicationEvent instanceof ApplicationReadyEvent) {
            Thread thd = new Thread(videoAnalysisTask);
            thd.start();
        }
    }
}
