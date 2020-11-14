package com.zhuanjingkj.zjcbe.business.service.dc;

import com.zhuanjingkj.zjcbe.business.dto.dc.QueryImageRecgDatasDTO;

public interface IImageRecgDatasService {
    public QueryImageRecgDatasDTO queryImageRecgDatas(
            String accessToken, String startTime, String endTime,
            int startIdx, int amount, int direction
    );
}
