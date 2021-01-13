package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DkRtvrMapper;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.data.dto.DkRtvrDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDkRtvrservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DkRtvrservice implements IDkRtvrservice {

    @Autowired
    private DkRtvrMapper dkRtvrMapper;

    @Override
    public List<DkRtvrDTO> getDkRtvrDTOs_exp() {
//        List<DkRtvrDTO> rtvrs = dkRtvrMapper.getTop2Violation();
//        if(rtvrs != null && rtvrs.size() > 0) {
//            for(int i = 0; i < rtvrs.size(); i++) {
//                //rtvrs.get(i).setImgUrl(IpfsClient.getIpfsUrl(rtvrs.get(i).getImageHash()));
//                rtvrs.get(i).setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg");
//            }
//        }
        List<DkRtvrDTO> rtvrs = new ArrayList<>();
        rtvrs.add(new DkRtvrDTO(101,101,"海淀区上地街道",101,"副驾驶未系安全带","大众牌-途观","2010","浙B·4184F",
                "2020-02-13 03:15:46",1,"http://192.168.2.68:9003/imgs/fjswjaqd.png"));
        rtvrs.add(new DkRtvrDTO(102,102,"朝阳区东湖渠街道",102,"主驾驶打电话","大众牌-朗逸","2008_2010_2011","浙A·2537M",
                "2020-04-23 16:15:46",2,"http://192.168.2.68:9003/imgs/zjsddh.png"));
        return rtvrs;
    }
}
