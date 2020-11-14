package com.zhuanjingkj.zjcbe.business.service.dc;

import com.zhuanjingkj.zjcbe.business.dto.dc.GetUserVideosDTO;

public interface IVideoRecgDataService {
    public GetUserVideosDTO getUserVideos(String accessToken);
}
