package com.zhuanjingkj.zjcbe.business.service.dc;

import com.zhuanjingkj.zjcbe.business.dto.dc.GetUserVideosDTO;
import com.zhuanjingkj.zjcbe.domain.entity.AccountAuthTokenEntity;
import com.zhuanjingkj.zjcbe.commondata.po.VideoStreamPO;
import com.zhuanjingkj.zjcbe.repository.account.AccountAuthTokenRepository;
import com.zhuanjingkj.zjcbe.repository.dc.DcVideoRecgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoRecgDataService implements IVideoRecgDataService {
    @Autowired
    AccountAuthTokenRepository accountAuthTokenRepository;
    @Autowired
    DcVideoRecgRepository dcVideoRecgRepository;

    @Override
    public GetUserVideosDTO getUserVideos(String accessToken) {
        AccountAuthTokenEntity entity = accountAuthTokenRepository.getAccountAuthToken(accessToken);
        System.out.println("### entity: " + entity + "; accessToken=" + accessToken + "!");
        int accountId = entity.getAccountId();
        System.out.println("### accountId=" + accountId + "!");
        List<VideoStreamPO> vss = dcVideoRecgRepository.getUserVideoStreamIds(accountId);
        for (VideoStreamPO po : vss) {
            System.out.println("############### po: " + po.getVideoStreamId() + "!");
        }
        GetUserVideosDTO dto = new GetUserVideosDTO();
        dto.setTotalNum(998);
        return dto;
    }
}
