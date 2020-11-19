package com.zhuanjingkj.stpbe.platform.service.Impl;


import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.platform.bo.TrafficFlowBO;
import com.zhuanjingkj.stpbe.platform.dto.DataKanbanDTO;
import com.zhuanjingkj.stpbe.platform.dto.TrafficFlowDTO;
import com.zhuanjingkj.stpbe.platform.dto.TrafficFlowItemDTO;
import com.zhuanjingkj.stpbe.platform.mapper.ImgVaHptzMapper;
import com.zhuanjingkj.stpbe.platform.service.ImgVaHptzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ImgVaHptzServiceImpl implements ImgVaHptzService {
    private static final String[] DURATIONS = {
            "00:00:00", "04:00:00", "08:00:00",
            "12:00:00", "16:00:00", "20:00:00",
            "24:00:00"
    };

    @Autowired
    ImgVaHptzMapper imgVaHptzMapper;

    @Override
    public ResultDTO<DataKanbanDTO> countTrafficFlow() {
        Date today = new Date();
        Date yesterday = new Date(today.getTime() - 86400000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = sdf.format(today);
        String yesterdayStr = sdf.format(yesterday);
        List<TrafficFlowItemDTO> todayTraffics = getDailyTrafficFlow(todayStr);
        List<TrafficFlowItemDTO> yesterdayTraffics = getDailyTrafficFlow(yesterdayStr);
        TrafficFlowDTO trafficFlow = new TrafficFlowDTO();
        trafficFlow.setTodayTraffics(todayTraffics);
        trafficFlow.setYesterdayTraffics(yesterdayTraffics);
        DataKanbanDTO data = new DataKanbanDTO();
        data.setTrafficFlow(trafficFlow);
        ResultDTO<DataKanbanDTO> dto = new ResultDTO<>();
        dto.setData(data);
        return dto;
    }

    private List<TrafficFlowItemDTO> getDailyTrafficFlow(String date) {
        List<TrafficFlowItemDTO> items = new ArrayList<>();
        String maxTime = null;
        String minTime = null;
        TrafficFlowItemDTO tfi = null;
        // 0至4时
        for (int i=0; i<6; i++) {
            minTime = date + " " + DURATIONS[i];
            maxTime = date + " " + DURATIONS[i+1];
            tfi = new TrafficFlowItemDTO();
            int count = imgVaHptzMapper.countByTime(maxTime,minTime);
            tfi.setCount(count);
            tfi.setReportTime(DURATIONS[i+1]);
            items.add(tfi);
        }
        return items;
    }
}
