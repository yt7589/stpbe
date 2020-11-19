package com.zhuanjingkj.stpbe.tmdp.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DkTitfService implements IDkTitfService {
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
}
