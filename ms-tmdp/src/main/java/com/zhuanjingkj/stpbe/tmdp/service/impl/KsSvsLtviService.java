package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.KsSvsLtviMapper;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.data.dto.KsSvsLtviDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsSvsLtviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KsSvsLtviService implements IKsSvsLtviService {

    @Autowired
    private KsSvsLtviMapper ksSvsLtviMapper;

    @Autowired
    private TvisJsonMapper tvisJsonMapper;

    @Override
    public KsSvsLtviDTO getKsSvsLtviDTO_exp() {
//        String tvisJsonTblName = tvisJsonMapper.getLatesTvisJsonTblName();
//        KsSvsLtviDTO ksSvsLtviDTO = ksSvsLtviMapper.getKsSvsLtvi(tvisJsonTblName);
//        //ksSvsLtviDTO.setImageUrl(IpfsClient.getIpfsUrl(ksSvsLtviDTO.getImageHash()));
//        ksSvsLtviDTO.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606650551241&di=8378d72dc6414bfa9a243c2e75db511a&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fauto%2Fpics%2Fhv1%2F246%2F190%2F1582%2F102918246.jpg");
        KsSvsLtviDTO ksSvsLtviDTO = new KsSvsLtviDTO(0,"海淀区上地八街","","",0,"副驾驶未系安全带","渝A-Z60Q1","一汽解放",0,0,"http://222.128.117.234:9003/imgs/tfjswjaqd.png",0,0,"2021-1-13 13:58:17");
        return ksSvsLtviDTO;
    }
}