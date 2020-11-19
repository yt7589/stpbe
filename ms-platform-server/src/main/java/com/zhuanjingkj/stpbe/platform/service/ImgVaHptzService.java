package com.zhuanjingkj.stpbe.platform.service;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.platform.bo.TrafficFlowBO;
import com.zhuanjingkj.stpbe.platform.dto.DataKanbanDTO;
import com.zhuanjingkj.stpbe.platform.dto.TrafficFlowDTO;

import java.util.List;

public interface ImgVaHptzService {

    /**
     * 计算交通当天和昨天的交通流量
     * @return
     */
    public ResultDTO<DataKanbanDTO> countTrafficFlow();
}
