package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DkRtvrMapper;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.data.dto.DkRtvrDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDkRtvrservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DkRtvrservice implements IDkRtvrservice {

    @Autowired
    private DkRtvrMapper dkRtvrMapper;

    @Override
    public List<DkRtvrDTO> getDkRtvrDTOs_exp() {
        List<DkRtvrDTO> rtvrs = dkRtvrMapper.getTop2Violation();
        if(rtvrs != null && rtvrs.size() > 0) {
            for(int i = 0; i < rtvrs.size(); i++) {
                String tblName = rtvrs.get(i).getTvisJsonTbl().replace("StpDb", "");
                long jsonId = rtvrs.get(i).getTvisJsonId();
                Map<String, Object> map = dkRtvrMapper.getImageHash(jsonId, tblName);
                rtvrs.get(i).setImgUrl(IpfsClient.getIpfsUrl("" + map.get("image_hash")));
            }
        }
//        List<DkRtvrDTO> rtvrs = new ArrayList<>();
//        rtvrs.add(new DkRtvrDTO(101,101,"海淀区上地街道",101,"副驾驶未系安全带","大众牌-途观","2010","浙B·4184F",
//                "2021-01-13 20:15:46",1,"http://222.128.117.234:9003/imgs/fjswjaqd.png"));
//        rtvrs.add(new DkRtvrDTO(102,102,"朝阳区东湖渠街道",102,"主驾驶打电话","大众牌-朗逸","2008_2010_2011","浙A·2537M",
//                "2021-01-13 20:20:46",2,"http://222.128.117.234:9003/imgs/zjsddh.png"));
        return rtvrs;
    }
}
