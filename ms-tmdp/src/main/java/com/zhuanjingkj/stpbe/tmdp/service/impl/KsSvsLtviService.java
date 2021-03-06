package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DkRtvrMapper;
import com.zhuanjingkj.stpbe.common.mapper.KsSvsLtviMapper;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.data.dto.KsSvsLtviDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsSvsLtviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KsSvsLtviService implements IKsSvsLtviService {

    @Autowired
    private KsSvsLtviMapper ksSvsLtviMapper;

    @Autowired
    private DkRtvrMapper dkRtvrMapper;

    @Override
    public KsSvsLtviDTO getKsSvsLtviDTO_exp() {
        KsSvsLtviDTO ksSvsLtviDTO = ksSvsLtviMapper.getKsSvsLtvi();
        if (ksSvsLtviDTO != null) {
            Map<String, Object> map = dkRtvrMapper.getImageHash(ksSvsLtviDTO.getTvisJsonId(), ksSvsLtviDTO.getTvisJsonTbl());
            if(map != null && map.size() > 0) {
                ksSvsLtviDTO.setImageUrl(IpfsClient.getIpfsUrl("" + map.get("image_hash")));
                ksSvsLtviDTO.setOccurTime("" + map.get("occur_time"));
            }
        }
        return ksSvsLtviDTO;
    }
}
