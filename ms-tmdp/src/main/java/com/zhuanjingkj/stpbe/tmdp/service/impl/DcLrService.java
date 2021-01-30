package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DcLrMapper;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcLrDTO;
import com.zhuanjingkj.stpbe.data.dto.DcLrSiteDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDcLrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DcLrService implements IDcLrService {

    @Autowired
    private DcLrMapper dcLrMapper;

    @Override
    public ResultDTO<DcLrDTO> queryLocusReplay_exp(String startTime, String endTime, String hphm) {
        ResultDTO<DcLrDTO> dto = new ResultDTO<>();
        DcLrDTO data = new DcLrDTO();
//        List<DcLrSiteDTO> recs = new ArrayList<>();
//        recs.add(new DcLrSiteDTO(102,"北京市海淀区西二旗街道19号",116.004004,40.20345));
//        recs.add(new DcLrSiteDTO(102,"北京市海淀区上地街道39号",116.007004,40.20445));
//        recs.add(new DcLrSiteDTO(102,"北京市海淀区西直门街道29号",116.009004,40.20845));
//        recs.add(new DcLrSiteDTO(102,"北京市海淀区知春路街道109号",116.014004,40.20945));
//        recs.add(new DcLrSiteDTO(102,"北京市朝阳区东湖区99号",116.034004,40.20745));
//        recs.add(new DcLrSiteDTO(102,"北京市昌平区北七家街道21号",116.084004,40.21045));
//        recs.add(new DcLrSiteDTO(102,"北京市望京街道59号",116.094004,40.21345));
//        recs.add(new DcLrSiteDTO(102,"北京市海淀区回龙观39号",116.044004,40.23345));
        List<DcLrSiteDTO> recs = dcLrMapper.getRouteInfo(startTime, endTime, hphm);
        data.setLrSite(recs);
        dto.setData(data);
        return dto;
    }
}
