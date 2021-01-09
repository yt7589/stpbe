package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.tmdp.dto.DkMvtsDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkRtvrDTO;
import com.zhuanjingkj.stpbe.tmdp.mapper.DkRtvrVtMapper;
import com.zhuanjingkj.stpbe.tmdp.service.IDkRtvrservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DkRtvrservice implements IDkRtvrservice {

    @Autowired
    private DkRtvrVtMapper dkRtvrMapper;

    @Override
    public List<DkRtvrDTO> getDkRtvrDTOs_exp() {
        List<DkRtvrDTO> rtvrs = dkRtvrMapper.getTop2Violation();
        if(rtvrs != null && rtvrs.size() > 0) {
            for(int i = 0; i < rtvrs.size(); i++) {
                rtvrs.get(i).setImgUrl(IpfsClient.getIpfsUrl(rtvrs.get(i).getImageHash()));
            }
        }

        return rtvrs;
    }
}
