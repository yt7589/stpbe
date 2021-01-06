package com.zhuanjingkj.stpbe.tmdp.task;

import com.zhuanjingkj.stpbe.tmdp.controller.TmdpWsHandler;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TmdpScheduledTask {
    private static int KS_AS_WS_SESS_CLEAN_TI = 60 * 1000;
    private static int ksAsWsSessCleanTi = 0;
    private static int KS_RSS_SFVS_COUNT = 0;
    private static int KS_RSS_LSVS_COUNT = 0;
    @Autowired
    private TmdpWsHandler tmdpWsHandler;
    @Autowired
    private RedisTemplate redisTemplate;
    @Async("tmdpPool")
    @Scheduled(cron = "*/1 * * * * ?")
    public void runTmdpScheduledTask() {
        System.out.println("发送WebSocket推送信息......");
        // 处理重点监管区域监管点位频繁经过车辆列表
        pushKsAsSfvsMsg();
        // 处理重点监管=》区域监管=》右侧监管动态列表
        pushKsAsLsvsMsg();
        // 处理重点监管特殊车辆监管最新违章信息
        //路段监管现场频繁车辆
        KS_RSS_SFVS_COUNT ++;
        if(KS_RSS_SFVS_COUNT > 5){
            pushKsRssSfvs();
            KS_RSS_SFVS_COUNT = 0;
        }
        //路段监管最新现场车辆
        KS_RSS_LSVS_COUNT ++;
        if(KS_RSS_LSVS_COUNT > 6){
            pushKsRssLsvs();
            KS_RSS_LSVS_COUNT = 0;
        }

        Integer hour = LocalDateTime.now().getHour() + LocalDateTime.now().getSecond() + LocalDateTime.now().getMinute();
        if(hour == 0) {
            resetHtfs();
        }
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

    private void pushKsAsLsvsMsg() {
        JSONArray data = new JSONArray();
        KsAsLsvDTO lsv = null;
        //
        lsv = new KsAsLsvDTO(1, 1, 101, "六里桥",
                "2020-12-19 16:50:39", "京A-XA001", 11);
        data.put(lsv.toJsonObject());
        //
        lsv = new KsAsLsvDTO(1, 1, 101, "六里桥",
                "2020-12-19 16:50:39", "京A-XA001", 11);
        data.put(lsv.toJsonObject());
        //
        lsv = new KsAsLsvDTO(1, 1, 101, "六里桥",
                "2020-12-19 16:50:39", "京A-XA001", 11);
        data.put(lsv.toJsonObject());
        //
        lsv = new KsAsLsvDTO(1, 1, 101, "六里桥",
                "2020-12-19 16:50:39", "京A-XA001", 11);
        data.put(lsv.toJsonObject());
        //
        lsv = new KsAsLsvDTO(1, 1, 101, "六里桥",
                "2020-12-19 16:50:39", "京A-XA001", 11);
        data.put(lsv.toJsonObject());
        //
        lsv = new KsAsLsvDTO(1, 1, 101, "六里桥",
                "2020-12-19 16:50:39", "京A-XA001", 11);
        data.put(lsv.toJsonObject());
        //
        lsv = new KsAsLsvDTO(1, 1, 101, "六里桥",
                "2020-12-19 16:50:39", "京A-XA001", 11);
        data.put(lsv.toJsonObject());
        //
        lsv = new KsAsLsvDTO(1, 1, 101, "六里桥",
                "2020-12-19 16:50:39", "京A-XA001", 11);
        data.put(lsv.toJsonObject());
        //
        lsv = new KsAsLsvDTO(1, 1, 101, "六里桥",
                "2020-12-19 16:50:39", "京A-XA001", 11);
        data.put(lsv.toJsonObject());
        //
        lsv = new KsAsLsvDTO(1, 1, 101, "六里桥",
                "2020-12-19 16:50:39", "京A-XA001", 11);
        data.put(lsv.toJsonObject());
        seq++;
        tmdpWsHandler.pushWsMsg(TmdpWsHandler.KS_AS_LSVS, data.toString());
    }

    private void pushKsRssSfvs() {
        JSONArray data = new JSONArray();

        KsRssSfvsDTO ksRssSfvsDTO = null;

        ksRssSfvsDTO = new KsRssSfvsDTO(101, "上地三街_" + seq,
                "京A-YB023", 12,
                116.31129731152342, 40.03570782927839);

        data.put(ksRssSfvsDTO.toJsonObject());

        ksRssSfvsDTO = new KsRssSfvsDTO(101, "上地三街_" + seq,
                "京A-YB023", 12,
                116.31129731152342, 40.03570782927839);

        data.put(ksRssSfvsDTO.toJsonObject());

        ksRssSfvsDTO = new KsRssSfvsDTO(101, "上地三街_" + seq,
                "京A-YB023", 12,
                116.31129731152342, 40.03570782927839);

        data.put(ksRssSfvsDTO.toJsonObject());

        ksRssSfvsDTO = new KsRssSfvsDTO(101, "上地三街_" + seq,
                "京A-YB023", 12,
                116.31129731152342, 40.03570782927839);

        data.put(ksRssSfvsDTO.toJsonObject());

        ksRssSfvsDTO = new KsRssSfvsDTO(101, "上地三街_" + seq,
                "京A-YB023", 12,
                116.31129731152342, 40.03570782927839);

        data.put(ksRssSfvsDTO.toJsonObject());

        ksRssSfvsDTO = new KsRssSfvsDTO(101, "上地三街_" + seq,
                "京A-YB023", 12,
                116.31129731152342, 40.03570782927839);

        data.put(ksRssSfvsDTO.toJsonObject());
        tmdpWsHandler.pushWsMsg(TmdpWsHandler.KS_RSS_SFVS, data.toString());
    }

    public void pushKsRssLsvs() {
        JSONArray data = new JSONArray();

        KsRssLsvsDTO ksRssLsvsDTO = null;

        ksRssLsvsDTO = new KsRssLsvsDTO(1, 1, 101, "B上地5街",
                "2020-12-19 16:50:39", "京A-XA001", 11);

        data.put(ksRssLsvsDTO.toJsonObject());

        ksRssLsvsDTO = new KsRssLsvsDTO(1, 1, 102, "B上地6街",
                "2020-12-19 16:50:39", "京A-XA001", 11);

        data.put(ksRssLsvsDTO.toJsonObject());

        ksRssLsvsDTO = new KsRssLsvsDTO(1, 1, 103, "B上地7街",
                "2020-12-19 16:50:39", "京A-XA001", 11);

        data.put(ksRssLsvsDTO.toJsonObject());

        ksRssLsvsDTO = new KsRssLsvsDTO(1, 1, 104, "B上地8街",
                "2020-12-19 16:50:39", "京A-XA001", 11);

        data.put(ksRssLsvsDTO.toJsonObject());

        ksRssLsvsDTO = new KsRssLsvsDTO(1, 1, 105, "上地9街",
                "2020-12-19 16:50:39", "京A-XA001", 11);

        data.put(ksRssLsvsDTO.toJsonObject());

        ksRssLsvsDTO = new KsRssLsvsDTO(1, 1, 106, "B上地10街",
                "2020-12-19 16:50:39", "京A-XA001", 11);

        data.put(ksRssLsvsDTO.toJsonObject());

        tmdpWsHandler.pushWsMsg(TmdpWsHandler.KS_RSS_SFVS, data.toString());
    }

    private void resetHtfs() {
        /**
         * 1.把今日过车量统计到本周过车量
         * 2.今日过车量重置为0
         * 3.如果当前日期为1号，重置本月累计过车量为0
         */
        Integer vToday = (int)(redisTemplate.opsForValue().get("dk_htfs_today") == null ? 0 : redisTemplate.opsForValue().get("dk_htfs_today"));
        redisTemplate.opsForList().leftPush("dk_htfs_week", vToday);
        if(redisTemplate.opsForList().size("dk_htfs_week") > 6) {
            redisTemplate.opsForList().rightPop("dk_htfs_week");
        }
        redisTemplate.opsForValue().set("dk_htfs_today",0); //重置今日过车数量
        Integer day = LocalDate.now().getDayOfMonth();
        if(day == 1) {
            redisTemplate.opsForValue().set("dk_htfs_month",0); //重置本月过车数量
        }

    }
}
