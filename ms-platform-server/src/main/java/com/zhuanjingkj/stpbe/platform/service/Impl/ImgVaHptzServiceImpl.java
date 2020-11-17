package com.zhuanjingkj.stpbe.platform.service.Impl;


import com.zhuanjingkj.stpbe.platform.bo.TrafficFlowBO;
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

    private static final String ZERO = " 00:00:00";
    private static final String FOUR = " 04:00:00";
    private static final String EIGHT = " 08:00:00";
    private static final String TWELVE = " 12:00:00";
    private static final String SIXTEEN = " 16:00:00";
    private static final String TWENTY = " 20:00:00";
    private static final String TWENTY_FOUR = " 24:00:00";

    @Autowired
    ImgVaHptzMapper imgVaHptzMapper;

    @Override
    public List<TrafficFlowBO> countTrafficFlow() {
        Date today = new Date();
        Date yesterday = new Date(today.getTime() - 86400000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = sdf.format(today);
        String yesterdayStr = sdf.format(yesterday);
        List<TrafficFlowBO> todyList = this.count(todayStr);
        List<TrafficFlowBO> yesterdayList  = this.count(yesterdayStr);
        yesterdayList.addAll(todyList);
        return yesterdayList;
    }
    private List<TrafficFlowBO> count(String date){
        List<TrafficFlowBO> list = new ArrayList<>();
        for(int i=0;i<6;i++){
            TrafficFlowBO trafficFlowBO = new TrafficFlowBO();
            String maxTime = null;
            String minTime = null;
            if(i==0){
                maxTime = date + FOUR;
                minTime = date + ZERO;
            }
            if(i==1){
                maxTime = date + EIGHT;
                minTime = date + FOUR;
            }
            if(i==2){
                maxTime = date + TWELVE;
                minTime = date + EIGHT;
            }
            if(i==3){
                maxTime = date + SIXTEEN;
                minTime = date + TWELVE;
            }
            if(i==4){
                maxTime = date + TWENTY;
                minTime = date + SIXTEEN;
            }
            if(i==5){
                maxTime = date + TWENTY_FOUR;
                minTime = date + TWENTY;
            }
            int count = imgVaHptzMapper.countByTime(maxTime,minTime);
            trafficFlowBO.setCount(count);
            trafficFlowBO.setReportTime(maxTime);
            list.add(trafficFlowBO);
        }
        return list;
    }
}
