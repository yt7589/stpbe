package com.zhuanjingkj.stpbe.tmdp.task;

import com.alibaba.fastjson.JSONObject;
import com.zhuanjingkj.stpbe.data.dto.KsAsLsvDTO;
import com.zhuanjingkj.stpbe.tmdp.controller.TmdpWsHandler;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.*;
import com.zhuanjingkj.stpbe.tmdp.service.impl.KsAsService;
import com.zhuanjingkj.stpbe.tmdp.service.impl.KsRssService;
import org.apache.commons.lang.StringUtils;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    }

    private static int seq = 0;
    private void pushKsAsSfvsMsg() {
        JSONArray data = new JSONArray();
        List<String> sfvs = redisTemplate.opsForList().range("ks_as_lsvs_list", 0, 9);
        KsRssSfvsDTO lsv = null;
        if(sfvs != null && sfvs.size() > 0) {
            for(int i = 0; i < sfvs.size(); i++) {
                String val = sfvs.get(i);
                if("0".equals(val)) {
                    continue;
                }
                if(StringUtils.isNotBlank(val)) {
                    String hphm = val.split("\\|")[0];
                    String cameraId = val.split("\\|")[1];
                    String coordinate = "" + KsAsService.areaSiteMap.get(cameraId);
                    if(StringUtils.isNotBlank(coordinate)) {
                        lsv = new KsRssSfvsDTO(101, "" + KsAsService.areaMap.get(cameraId),
                                hphm, Integer.parseInt("" + redisTemplate.opsForHash().get("ks_as_lsvs_total", val)), Double.parseDouble(coordinate.split("\\|")[0]),Double.parseDouble(coordinate.split("\\|")[1]));
                        data.put(lsv.toJsonObject());
                    }
                }
            }
        }
//        KsAsSfvDTO sfv = null;
//        //
//        sfv = new KsAsSfvDTO(101, "上地三街_" + seq,
//                "京A-YB023", 12,
//                116.31129731152342, 40.03570782927839);
//        data.put(sfv.toJsonObject());
//        sfv = new KsAsSfvDTO(101, "西直门_" + seq,
//                "京B-AB987", 12,
//                116.38129731152342, 40.13570782927839);
//        data.put(sfv.toJsonObject());
        seq++;
        tmdpWsHandler.pushWsMsg(TmdpWsHandler.KS_AS_SFVS, data.toString());
    }

    private void pushKsAsLsvsMsg() {
        JSONArray data = new JSONArray();
        List<String> lsvs = redisTemplate.opsForList().range("ks_as_lsvs_list", 0, 9);
        KsAsLsvDTO lsv = null;
        if(lsvs != null && lsvs.size() > 0) {
            for(int i = 0; i < lsvs.size(); i++) {
                String val = lsvs.get(i);
                if("0".equals(val)) {
                    continue;
                }
                if(StringUtils.isNotBlank(val)) {
                    String hphm = val.split("\\|")[0];
                    String cameraId = val.split("\\|")[1];
                    lsv = new KsAsLsvDTO(1,1,1, "" + KsAsService.areaMap.get(cameraId),
                            "" + redisTemplate.opsForHash().get("ks_as_lsvs_time", val),hphm,
                            Integer.parseInt("" + redisTemplate.opsForHash().get("ks_as_lsvs_total", val)));
                    data.put(lsv.toJsonObject());
                }
            }
        }
//        KsAsLsvDTO lsv = null;
//        //
//        lsv = new KsAsLsvDTO(1, 1, 101, "西直门",
//                "2021-01-14 12:50:39", "京KZ8601", 1);
//        data.put(lsv.toJsonObject());
//        //
//        lsv = new KsAsLsvDTO(1, 1, 101, "大钟寺",
//                "2021-01-13 13:50:39", "京KZ8601", 3);
//        data.put(lsv.toJsonObject());
//        //
//        lsv = new KsAsLsvDTO(1, 1, 101, "知春路",
//                "2021-01-12 16:50:39", "豫AF52301X", 5);
//        data.put(lsv.toJsonObject());
//        //
//        lsv = new KsAsLsvDTO(1, 1, 101, "五道口",
//                "2021-01-11 11:50:39", "苏FTET721", 6);
//        data.put(lsv.toJsonObject());
//        //
//        lsv = new KsAsLsvDTO(1, 1, 101, "上地",
//                "2020-01-10 10:50:39", "鲁P7ET79", 1);
//        data.put(lsv.toJsonObject());
//        //
//        lsv = new KsAsLsvDTO(1, 1, 101, "清河",
//                "2020-12-29 12:50:39", "鲁P7ET15", 2);
//        data.put(lsv.toJsonObject());
//        //
//        lsv = new KsAsLsvDTO(1, 1, 101, "西二旗",
//                "2020-12-19 13:50:39", "京P7ET75", 5);
//        data.put(lsv.toJsonObject());
//        //
//        lsv = new KsAsLsvDTO(1, 1, 101, "龙泽",
//                "2020-12-18 14:50:39", "赣P7ET75", 7);
//        data.put(lsv.toJsonObject());
//        //
//        lsv = new KsAsLsvDTO(1, 1, 101, "回龙观",
//                "2020-12-17 15:50:39", "贵P7ET75", 1);
//        data.put(lsv.toJsonObject());
//        //
//        lsv = new KsAsLsvDTO(1, 1, 101, "立水桥",
//                "2020-12-16 16:50:39", "豫A52301X", 9);
        seq++;
        tmdpWsHandler.pushWsMsg(TmdpWsHandler.KS_AS_LSVS, data.toString());
    }

    private void pushKsRssSfvs() {
        JSONArray data = new JSONArray();
        List<String> sfvs = redisTemplate.opsForList().range("ks_rss_lsvs_list", 0, 9);
        KsRssSfvsDTO lsv = null;
        if(sfvs != null && sfvs.size() > 0) {
            for(int i = 0; i < sfvs.size(); i++) {
                String val = sfvs.get(i);
                if("0".equals(val)) {
                    continue;
                }
                if(StringUtils.isNotBlank(val)) {
                    String hphm = val.split("\\|")[0];
                    String cameraId = val.split("\\|")[1];
                    String coordinate = "" + KsRssService.rssSiteMap.get(cameraId);
                    if(StringUtils.isNotBlank(coordinate)) {
                        lsv = new KsRssSfvsDTO(101, "" + KsRssService.rssMap.get(cameraId),
                                hphm, Integer.parseInt("" + redisTemplate.opsForHash().get("ks_rss_lsvs_total", val)),
                                Double.parseDouble(coordinate.split("\\|")[0]),Double.parseDouble(coordinate.split("\\|")[1]));
                        data.put(lsv.toJsonObject());
                    }
                }
            }
        }
//        KsRssSfvsDTO ksRssSfvsDTO = null;
//
//        ksRssSfvsDTO = new KsRssSfvsDTO(101, "上地三街_" + seq,
//                "京A-YB023", 12,
//                116.31129731152342, 40.03570782927839);
//
//        data.put(ksRssSfvsDTO.toJsonObject());
//
//        ksRssSfvsDTO = new KsRssSfvsDTO(101, "上地三街_" + seq,
//                "京A-YB023", 12,
//                116.31129731152342, 40.03570782927839);
//
//        data.put(ksRssSfvsDTO.toJsonObject());
//
//        ksRssSfvsDTO = new KsRssSfvsDTO(101, "上地三街_" + seq,
//                "京A-YB023", 12,
//                116.31129731152342, 40.03570782927839);
//
//        data.put(ksRssSfvsDTO.toJsonObject());
//
//        ksRssSfvsDTO = new KsRssSfvsDTO(101, "上地三街_" + seq,
//                "京A-YB023", 12,
//                116.31129731152342, 40.03570782927839);
//
//        data.put(ksRssSfvsDTO.toJsonObject());
//
//        ksRssSfvsDTO = new KsRssSfvsDTO(101, "上地三街_" + seq,
//                "京A-YB023", 12,
//                116.31129731152342, 40.03570782927839);
//
//        data.put(ksRssSfvsDTO.toJsonObject());
//
//        ksRssSfvsDTO = new KsRssSfvsDTO(101, "上地三街_" + seq,
//                "京A-YB023", 12,
//                116.31129731152342, 40.03570782927839);

        tmdpWsHandler.pushWsMsg(TmdpWsHandler.KS_RSS_SFVS, data.toString());
    }

    public void pushKsRssLsvs() {
        JSONArray data = new JSONArray();
        List<String> sfvs = redisTemplate.opsForList().range("ks_rss_lsvs_list", 0, 9);
        KsRssLsvsDTO lsv = null;
        if(sfvs != null && sfvs.size() > 0) {
            for(int i = 0; i < sfvs.size(); i++) {
                String val = sfvs.get(i);
                if("0".equals(val)) {
                    continue;
                }
                if(StringUtils.isNotBlank(val)) {
                    String hphm = val.split("\\|")[0];
                    String cameraId = val.split("\\|")[1];
                    String coordinate = "" + KsAsService.areaSiteMap.get(cameraId);
                    if(StringUtils.isNotBlank(coordinate)) {
                        lsv = new KsRssLsvsDTO(1, 1,1, "" + KsRssService.rssMap.get(cameraId), "" +redisTemplate.opsForHash().get("ks_rss_lsvs_time", val),
                        hphm, Integer.parseInt("" + redisTemplate.opsForHash().get("ks_rss_lsvs_total", val)));
                        data.put(lsv.toJsonObject());
                    }
                }
            }
        }
//        KsRssLsvsDTO ksRssLsvsDTO = null;
//
//        ksRssLsvsDTO = new KsRssLsvsDTO(1, 1, 101, "B上地5街",
//                "2020-12-19 16:50:39", "京A-XA001", 11);
//
//        data.put(ksRssLsvsDTO.toJsonObject());
//
//        ksRssLsvsDTO = new KsRssLsvsDTO(1, 1, 102, "B上地6街",
//                "2020-12-19 16:50:39", "京A-XA001", 11);
//
//        data.put(ksRssLsvsDTO.toJsonObject());
//
//        ksRssLsvsDTO = new KsRssLsvsDTO(1, 1, 103, "B上地7街",
//                "2020-12-19 16:50:39", "京A-XA001", 11);
//
//        data.put(ksRssLsvsDTO.toJsonObject());
//
//        ksRssLsvsDTO = new KsRssLsvsDTO(1, 1, 104, "B上地8街",
//                "2020-12-19 16:50:39", "京A-XA001", 11);
//
//        data.put(ksRssLsvsDTO.toJsonObject());
//
//        ksRssLsvsDTO = new KsRssLsvsDTO(1, 1, 105, "上地9街",
//                "2020-12-19 16:50:39", "京A-XA001", 11);
//
//        data.put(ksRssLsvsDTO.toJsonObject());
//
//        ksRssLsvsDTO = new KsRssLsvsDTO(1, 1, 106, "B上地10街",
//                "2020-12-19 16:50:39", "京A-XA001", 11);
        tmdpWsHandler.pushWsMsg(TmdpWsHandler.KS_RSS_SFVS, data.toString());
    }

}
