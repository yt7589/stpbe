package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.AppRegistry;
import com.zhuanjingkj.stpbe.data.dto.LoginDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.LoginRTO;
import com.zhuanjingkj.stpbe.db.DaoEngine;
import com.zhuanjingkj.stpbe.db.DataSourceRegistry;
import com.zhuanjingkj.stpbe.tmdp.dto.DkMainDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkTitfDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkTitfItemDTO;
import com.zhuanjingkj.stpbe.tmdp.mapper.DkTitfMapper;
import com.zhuanjingkj.stpbe.tmdp.service.IDkTitfService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DkTitfService implements IDkTitfService {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String[] DURATIONS = {
            "00:00:00", "04:00:00", "08:00:00",
            "12:00:00", "16:00:00", "20:00:00",
            "24:00:00"
    };

    @Autowired
    private DkTitfMapper dkTitfMapper;

    @Override
    public DkTitfDTO countTrafficFlow() {
        Date today = new Date();
        Date yesterday = new Date(today.getTime() - 86400000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = sdf.format(today);
        String yesterdayStr = sdf.format(yesterday);
        List<DkTitfItemDTO> todayTraffics = getDailyTrafficFlow(todayStr);
        List<DkTitfItemDTO> yesterdayTraffics = getDailyTrafficFlow(yesterdayStr);
        DkTitfDTO trafficFlow = new DkTitfDTO();
        trafficFlow.setTodayTraffics(todayTraffics);
        trafficFlow.setYesterdayTraffics(yesterdayTraffics);
        return trafficFlow;
    }

    private List<DkTitfItemDTO> getDailyTrafficFlow(String date) {
        List<DkTitfItemDTO> items = new ArrayList<>();
        String maxTime = null;
        String minTime = null;
        DkTitfItemDTO tfi = null;
        // 0至4时
        for (int i=0; i<6; i++) {
            minTime = date + " " + DURATIONS[i];
            maxTime = date + " " + DURATIONS[i+1];
            tfi = new DkTitfItemDTO();
            int count = dkTitfMapper.countByTime(maxTime,minTime);
            tfi.setCount(count);
            tfi.setReportTime(DURATIONS[i+1]);
            items.add(tfi);
        }
        return items;
    }

    @Override
    public DkTitfDTO getDkTitfDTO_exp() {
        String zt = DateUtil.plusDays(-1);
        String jt = DateUtil.plusDays(0);
        DkTitfDTO titf = new DkTitfDTO();
        DkTitfItemDTO item;
        // 昨天
        List<DkTitfItemDTO> yesterday = new ArrayList<>();
        // 昨天4时
        item = new DkTitfItemDTO();
        item.setReportTime("4");
        item.setCount((int)(redisTemplate.opsForValue().get("dk_titf_" + zt + "04") == null ? 0 : redisTemplate.opsForValue().get("dk_titf_" + zt + "04")));
        yesterday.add(item);
        // 昨天8时
        item = new DkTitfItemDTO();
        item.setReportTime("8");
        item.setCount((int)(redisTemplate.opsForValue().get("dk_titf_" + zt + "08") == null ? 0 : redisTemplate.opsForValue().get("dk_titf_" + zt + "08")));
        yesterday.add(item);
        // 昨天12时
        item = new DkTitfItemDTO();
        item.setReportTime("12");
        item.setCount((int)(redisTemplate.opsForValue().get("dk_titf_" + zt + "12") == null ? 0 : redisTemplate.opsForValue().get("dk_titf_" + zt + "12")));
        yesterday.add(item);
        // 昨天16时
        item = new DkTitfItemDTO();
        item.setReportTime("16");
        item.setCount((int)(redisTemplate.opsForValue().get("dk_titf_" + zt + "16") == null ? 0 : redisTemplate.opsForValue().get("dk_titf_" + zt + "16")));
        yesterday.add(item);
        // 昨天20时
        item = new DkTitfItemDTO();
        item.setReportTime("20");
        item.setCount((int)(redisTemplate.opsForValue().get("dk_titf_" + zt + "20") == null ? 0 : redisTemplate.opsForValue().get("dk_titf_" + zt + "20")));
        yesterday.add(item);
        // 昨天24时
        item = new DkTitfItemDTO();
        item.setReportTime("24");
        item.setCount((int)(redisTemplate.opsForValue().get("dk_titf_" + zt + "24") == null ? 0 : redisTemplate.opsForValue().get("dk_titf_" + zt + "24")));
        yesterday.add(item);
        // 今天
        List<DkTitfItemDTO> today = new ArrayList<>();
        // 今天4时
        item = new DkTitfItemDTO();
        item.setReportTime("4");
        item.setCount((int)(redisTemplate.opsForValue().get("dk_titf_" + jt + "04") == null ? 0 : redisTemplate.opsForValue().get("dk_titf_" + jt + "04")));
        today.add(item);
        // 今天8时
        item = new DkTitfItemDTO();
        item.setReportTime("8");
        item.setCount((int)(redisTemplate.opsForValue().get("dk_titf_" + jt + "08") == null ? 0 : redisTemplate.opsForValue().get("dk_titf_" + jt + "08")));
        today.add(item);
        // 今天12时
        item = new DkTitfItemDTO();
        item.setReportTime("12");
        item.setCount((int)(redisTemplate.opsForValue().get("dk_titf_" + jt + "12") == null ? 0 : redisTemplate.opsForValue().get("dk_titf_" + jt + "12")));
        today.add(item);
        // 今天16时
        item = new DkTitfItemDTO();
        item.setReportTime("16");
        item.setCount((int)(redisTemplate.opsForValue().get("dk_titf_" + jt + "16") == null ? 0 : redisTemplate.opsForValue().get("dk_titf_" + jt + "16")));
        today.add(item);
        // 今天20时
        item = new DkTitfItemDTO();
        item.setReportTime("20");
        item.setCount((int)(redisTemplate.opsForValue().get("dk_titf_" + jt + "20") == null ? 0 : redisTemplate.opsForValue().get("dk_titf_" + jt + "20")));
        today.add(item);
        // 今天24时
        item = new DkTitfItemDTO();
        item.setReportTime("24");
        item.setCount((int)(redisTemplate.opsForValue().get("dk_titf_" + jt + "24") == null ? 0 : redisTemplate.opsForValue().get("dk_titf_" + jt + "24")));
        today.add(item);
        titf.setYesterdayTraffics(yesterday);
        titf.setTodayTraffics(today);
        return titf;
    }

}
