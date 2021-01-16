package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.KsRssMapper;
import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.AddRsToRssRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.DeleteRsFromRssRTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsRssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KsRssService implements IKsRssService {

    @Autowired
    private KsRssMapper ksRssMapper;

    @Override
    public ResultDTO<DbQrsDTO> queryRsSupervision_exp(String rssName, Integer startIndex, Integer amount, Integer direction, Integer type) {
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        Integer count = ksRssMapper.getKsRoadCount(rssName, type);
        ResultDTO<DbQrsDTO> dto = new ResultDTO<DbQrsDTO>();
        List<KsRssDTO> recs = ksRssMapper.getKsRoad(rssName, startIndex, amount, type);
        DbQrsDTO data = new DbQrsDTO(100,5,0,10,5,recs);
//        List<KsRssDTO> recs = new ArrayList<>();
//        recs.add(new KsRssDTO(101, "北京市海淀区上龙泽23号", 0,  "10001"));
//        recs.add(new KsRssDTO(102, "北京市海淀区回龙观39号", 1,  "100021"));
//        recs.add(new KsRssDTO(103, "北京市望京街道59号", 1,  "100022"));
//        recs.add(new KsRssDTO(104, "北京市昌平区北七家街道21号", 1,  "100023"));
//        recs.add(new KsRssDTO(105, "北京市海淀区知春路街道109号", 1,  "100024"));
//        recs.add(new KsRssDTO(106, "北京市朝阳区东湖区99号", 1,  "100025"));
//        recs.add(new KsRssDTO(107, "北京市海淀区上地街道39号", 1,  "100026"));
//        recs.add(new KsRssDTO(108, "北京市海淀区西二旗街道19号", 1,  "100027"));
//        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> deleteKsRsSupervision_exp(DeleteRsFromRssRTO rto) {
        System.out.println("delete rss: " + rto.getRssId() + "!");
        Integer affectedRows = ksRssMapper.deleteKsRoad(rto.getRssId());
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbInsertResultDTO> addRsToRsSupervision_exp(AddRsToRssRTO rto) {
        List<Integer> rssIds = rto.getRssIds();
        for (int i = 0 ; i < rssIds.size() ; i ++) {
            System.out.println("rssId:" + rssIds.get(i));
        }
        Integer affectedRows = ksRssMapper.addRoads(rssIds);
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<>();
        DbInsertResultDTO data = new DbInsertResultDTO(0, affectedRows);
        dto.setData(data);
        return dto;
    }
}
