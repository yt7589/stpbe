package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.KsAsMapper;
import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.AddAreasToKeyAreasRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.ks.DeleteAreaFromKeyAreasRTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsAsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KsAsService implements IKsAsService {

    @Autowired
    private KsAsMapper ksAsMapper;

    public static Map<String, Object> areaMap = new HashMap<>();

    @Override
    public ResultDTO<DbQrsDTO> queryKeyAreas_exp(String areaName, Integer startIndex, Integer amount, Integer direction, Integer type) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        Integer count = ksAsMapper.getAreaCount(areaName, type);
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<AreaDTO> recs = ksAsMapper.getKsArea(areaName, startIndex, amount, type);
        DbQrsDTO data = new DbQrsDTO(count, recs.size(), startIndex, amount, direction,recs);
//        List<AreaDTO> recs = new ArrayList<>();
//        recs.add(new AreaDTO(101, "上地", 0, 3, "1_1_1"));
//        recs.add(new AreaDTO(102, "五道口", 1, 3, "1_1_2"));
//        recs.add(new AreaDTO(103, "东直门", 1, 3, "1_1_2"));
//        recs.add(new AreaDTO(104, "动物园", 1, 3, "1_1_2"));
//        recs.add(new AreaDTO(105, "新街口", 1, 3, "1_1_2"));
//        recs.add(new AreaDTO(106, "六里桥", 1, 3, "1_1_2"));
//        recs.add(new AreaDTO(107, "车道沟", 1, 3, "1_1_2"));
//        recs.add(new AreaDTO(108, "朝阳门", 1, 3, "1_1_2"));
//        recs.add(new AreaDTO(109, "人大双安", 1, 3, "1_1_2"));
//        recs.add(new AreaDTO(110, "联想桥", 1, 3, "1_1_2"));
//        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> deleteAreaFromKeyAreas_exp(DeleteAreaFromKeyAreasRTO rto) {
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        Integer affectedRows = ksAsMapper.deleteArea(rto.getAreaId());
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbInsertResultDTO> addAreasToKeyAreas_exp(AddAreasToKeyAreasRTO rto) {
        List<Integer> areas = rto.getAreas();
        for (int area : areas) {
            System.out.println("add area: " + area + "!");
        }
        Integer affectedRows = ksAsMapper.addAreas(areas);
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<>();
        DbInsertResultDTO data = new DbInsertResultDTO(0, affectedRows);
        dto.setData(data);
        return dto;
    }

    @PostConstruct
    private void init() {
        List<Map<String, Object>> areas = ksAsMapper.getKsAreaCode();
        for (int i = 0; i < areas.size(); i++) {
            areaMap.put("" + areas.get(i).get("camera_code"), areas.get(i).get("area_name"));
        }
    }
}
