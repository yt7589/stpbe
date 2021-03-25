package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.mapper.TvisJsonMapper;
import com.zhuanjingkj.stpbe.common.mgq.GrqEngine;
import com.zhuanjingkj.stpbe.common.tvis.TvisUtil;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.vo.TvisGrqRstVo;
import com.zhuanjingkj.stpbe.data.vo.TvisJsonVO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcCsDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDcCsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DcCsService implements IDcCsService {
    @Autowired
    private TvisJsonMapper tvisJsonMapper;

    @Override
    public ResultDTO<DbQrsDTO> queryVehicleByGraph(String cltzxl, String psfx,
                                                   String cllxfl, String cllxzfl,
                                                   String startDate, String endDate,
                                                   String startTime, String endTime,
                                                   int startIndex, int amount) {
        // 生成查询条件
        List<List<Float>> embeddinbs = new ArrayList<>();
        List<Float> embedding = new ArrayList<>();
        String[] feats = cltzxl.split(",");
        for (String feat : feats) {
            embedding.add(Float.valueOf(feat));
        }
        embeddinbs.add(embedding);
        String partitionTag = GrqEngine.getPartitionTag(psfx, cllxfl, cllxzfl);
        long topK = 100; // 以图搜车返回的记录数
        List<TvisGrqRstVo> results = GrqEngine.findTopK(partitionTag, embeddinbs, topK);
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        DbQrsDTO data = new DbQrsDTO(100,20,0,20,0,null);
        List<DcCsDTO> recs = new ArrayList<>();
        DcCsDTO rec = null;
        TvisJsonVO tvisJsonVO = null;
        int idx = 0;
        for (TvisGrqRstVo result : results) {
            tvisJsonVO = TvisUtil.getTvisJsonVOById(tvisJsonMapper, result.getTvisJsonId());
            if (tvisJsonVO != null) {
                if (idx<startIndex) {
                    continue;
                }
                tvisJsonVO.setVehIdx(result.getVehsIdx());
                rec = new DcCsDTO(tvisJsonVO.getTvisJsonId(), "北京市" + tvisJsonVO.getTvisJsonId(),
                        tvisJsonVO.getOccurTime(),
                        PropUtil.getValue("IPFS_GW_URL") + tvisJsonVO.getImageHash());
                recs.add(rec);
                idx++;
                if (idx >= startIndex + amount) {
                    break;
                }
            }
        }
        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }
}
