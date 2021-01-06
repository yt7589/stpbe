package com.zhuanjingkj.stpbe.tmdp.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoAnalysisTask {
    private static List<String> streamIds = new ArrayList<>();

    @Async("tmdpPool")
    @Scheduled(cron = "*/1 * * * * ?")
    public void runVideoAnalysisTask() {
        for (String streamId : streamIds) {
            System.out.println("##### 处理第" + (Long.parseLong(streamId) + 1) + "路视频...");
        }
    }

    public static void putStream(long streamId) {
        System.out.println("加入到视频列表中...");
        String streamIdKey = "" + streamId;
        if (!streamIds.contains(streamIdKey)) {
            streamIds.add(streamIdKey);
        }
    }
}
