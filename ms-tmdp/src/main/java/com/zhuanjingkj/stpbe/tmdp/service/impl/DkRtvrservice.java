package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DkRtvrMapper;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.data.dto.DkRtvrDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDkRtvrservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DkRtvrservice implements IDkRtvrservice {

    @Autowired
    private DkRtvrMapper dkRtvrMapper;

    @Override
    public List<DkRtvrDTO> getDkRtvrDTOs_exp() {
        List<DkRtvrDTO> rtvrs = dkRtvrMapper.getTop2Violation();
        if(rtvrs != null && rtvrs.size() > 0) {
            for(int i = 0; i < rtvrs.size(); i++) {
                //rtvrs.get(i).setImgUrl(IpfsClient.getIpfsUrl(rtvrs.get(i).getImageHash()));
                rtvrs.get(i).setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg");
            }
        }

        return rtvrs;
    }
}
