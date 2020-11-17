package com.zhuanjingkj.stpbe.platform.service;

import com.zhuanjingkj.stpbe.platform.bo.TrafficFlowBO;

import java.util.List;

public interface ImgVaHptzService {

    /**
     * 计算交通当天和昨天的交通流量
     * @return
     */
    List<TrafficFlowBO> countTrafficFlow();
}
