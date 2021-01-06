package com.zhuanjingkj.stpbe.tmdp.task;

import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoAnalysisTask {
    @Autowired
    private TvisJsonMapper tvisJsonMapper;
    private static List<String> streamIds = new ArrayList<>();

    @Async("tmdpPool")
    @Scheduled(cron = "*/1 * * * * ?")
    public void runVideoAnalysisTask() {
        /*if (StringUtils.isBlank(AppRegistry.tvisJsonTblName)) {
            // 获取当前t_tvis_json_*表名
            AppRegistry.tvisJsonTblName = tvisJsonMapper.getLatesTvisJsonTblName();
        }
        for (String streamId : streamIds) {
            System.out.println("##### 处理第" + (Long.parseLong(streamId) + 1) + "路视频...");
            // 找到当前原始信息表
            System.out.println("##### 表名：" + AppRegistry.tvisJsonTblName + "!");
        }*/
    }

    public static void putStream(long streamId) {
        System.out.println("加入到视频列表中...");
        String streamIdKey = "" + streamId;
        if (!streamIds.contains(streamIdKey)) {
            streamIds.add(streamIdKey);
        }
    }
}
