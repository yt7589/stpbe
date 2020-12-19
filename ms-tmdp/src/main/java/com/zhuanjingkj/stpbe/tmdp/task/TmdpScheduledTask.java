package com.zhuanjingkj.stpbe.tmdp.task;

import com.zhuanjingkj.stpbe.tmdp.controller.TmdpWsHandler;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsAsSfvDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TmdpScheduledTask {
    @Autowired
    private TmdpWsHandler tmdpWsHandler;

    @Async("tmdpPool")
    @Scheduled(cron = "*/1 * * * * ?")
    public void runTmdpScheduledTask() {
        System.out.println("发送WebSocket推送信息......");
        // 处理重点监管区域监管点位频繁经过车辆列表
        pushKsAsSfvsMsg();
        // 处理重点监管特殊车辆监管最新违章信息
    }

    private static int seq = 0;
    private void pushKsAsSfvsMsg() {
        JSONArray data = new JSONArray();
        KsAsSfvDTO sfv = null;
        //
        sfv = new KsAsSfvDTO(101, "上地三街_" + seq,
                "京A-YB023", 12,
                116.31129731152342, 40.03570782927839);
        data.put(sfv.toJsonObject());
        sfv = new KsAsSfvDTO(101, "西直门_" + seq,
                "京B-AB987", 12,
                116.38129731152342, 40.13570782927839);
        data.put(sfv.toJsonObject());
        seq++;
        tmdpWsHandler.pushWsMsg(TmdpWsHandler.KS_AS_SFVS, data.toString());
    }
}
