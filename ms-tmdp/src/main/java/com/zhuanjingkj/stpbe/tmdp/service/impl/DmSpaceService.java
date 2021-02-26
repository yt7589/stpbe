package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DmSpaceMapper;
import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.data.rto.dm.AddAreaToSpaceRTO;
import com.zhuanjingkj.stpbe.data.rto.dm.UpdateSpaceAreaRTO;
import com.zhuanjingkj.stpbe.tmdp.rto.dm.DeleteAreaFromSpaceRTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDmSpaceService;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DmSpaceService implements IDmSpaceService {

    @Autowired
    private DmSpaceMapper dmSpaceMapper;

    @Override
    public ResultDTO<DbQrsDTO> queryArea_exp(Integer startIndex, Integer amount, Integer direction) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<DmAmAreaDTO> recs = dmSpaceMapper.getSpaceArea(startIndex, amount);
        Integer count = dmSpaceMapper.getSpaceAreaCount();
        DbQrsDTO data = new DbQrsDTO(count,recs.size(),startIndex,amount,direction,recs);
//        List<DmAmAreaDTO> recs = new ArrayList<>();
//        recs.add(new DmAmAreaDTO(103,"圆明园西路","143456","海淀区"));
//        recs.add(new DmAmAreaDTO(104,"朝阳区","153456","北京市"));
//        recs.add(new DmAmAreaDTO(103,"上地","163456","海淀区"));
//        recs.add(new DmAmAreaDTO(104,"海淀区","173456","海淀区"));
//        recs.add(new DmAmAreaDTO(103,"西直门","183456","海淀区"));
//        recs.add(new DmAmAreaDTO(104,"东直门","193456","朝阳区"));
//        recs.add(new DmAmAreaDTO(103,"惠新西街","223456","海淀区"));
//        recs.add(new DmAmAreaDTO(104,"石景山区","323456","北京市"));
//        recs.add(new DmAmAreaDTO(103,"门头沟区","423456","北京市"));
//        recs.add(new DmAmAreaDTO(104,"朝阳区","523456","北京市"));
//        recs.add(new DmAmAreaDTO(103,"通州区","623456","北京市"));
//        recs.add(new DmAmAreaDTO(104,"朝阳区","723456","北京市"));
//        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbInsertResultDTO> addAreaToSpace_exp(AddAreaToSpaceRTO rto) {
        System.out.println(rto.getAreaName());
        Integer parentId = rto.getParentId();
        Map<String, Object> parentInfo = dmSpaceMapper.getSpaceAreaInfo(parentId);
        Integer level = Integer.parseInt(parentInfo.get("level") + "") + 1;
        String code = parentInfo.get("group_code") + "";
        String maxCode = dmSpaceMapper.getSpaceMaxCode(code  + "#_");
        String newcode = "";
        if(StringUtils.isNotBlank(maxCode)) {
            String[] str = maxCode.split("_");
            /**
             * 如果str.length = level 说明maxCode是子节点，反之为父节点
             */
            if(str.length == level) {
                for(int i = 0; i < str.length - 1; i++) {
                    newcode += str[i] +"_";
                }
                newcode = newcode + (Integer.parseInt(str[str.length -1]) + 1);
            }
        }  else {
            newcode = code + "_1";
        }
        Integer affectedRows = dmSpaceMapper.insertDmSpace(rto, newcode, level);
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<>();
        DbInsertResultDTO data = new DbInsertResultDTO(rto.getAreaId(), affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> updateArea_exp(UpdateSpaceAreaRTO rto) {
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        Integer affectedRows = dmSpaceMapper.updateAreaInfo(rto);
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> deleteAreaFromSpace_exp(DeleteAreaFromSpaceRTO rto) {
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        Integer affectedRows = dmSpaceMapper.deleteSpaceArea(rto.getAreaId());
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }
}
