package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.KsVcMapper;
import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.data.rto.ks.AddVehicleToVcRTO;
import com.zhuanjingkj.stpbe.data.rto.ks.DeleteVehicleFromVcRTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsVcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KsVcService implements IKsVcService {

    @Autowired
    private KsVcMapper ksvcServiceMaper;

    @Override
    public ResultDTO<DbQrsDTO> queryVehicle_exp(String hphm, Integer startIndex, Integer amount, Integer direction) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        Integer count = ksvcServiceMaper.getKsvcCount(hphm);
        List<KsVcDTO> recs = ksvcServiceMaper.getKsvc(hphm, startIndex, amount);
        DbQrsDTO data = new DbQrsDTO(count,recs.size(),startIndex,amount,direction,recs);
//        List<KsVcDTO> recs = new ArrayList<>();
//        recs.add(new KsVcDTO(102, "苏AL6H87"));
//        recs.add(new KsVcDTO(102, "豫A52301X"));
//        recs.add(new KsVcDTO(102, "苏GW81752"));
//        recs.add(new KsVcDTO(102, "苏AL9687"));
//        recs.add(new KsVcDTO(102, "鲁C817S2"));
//        recs.add(new KsVcDTO(102, "津KL9687"));
//        recs.add(new KsVcDTO(102, "蒙Q81752"));
//        recs.add(new KsVcDTO(102, "鲁KL9687"));
//        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbInsertResultDTO> addVehicle_exp(AddVehicleToVcRTO rto) {
        System.out.println("添加车牌号：" + rto.getHphm());
        ksvcServiceMaper.addVehicle(rto);
        Integer primaryKey = rto.getVcId();
        Integer affectedRows = 0;
        if(primaryKey > 0) {
            affectedRows = 1;
        } else {
            primaryKey = 0;
        }
        ResultDTO<DbInsertResultDTO> dto = new ResultDTO<DbInsertResultDTO>();
        DbInsertResultDTO data = new DbInsertResultDTO(primaryKey,affectedRows);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbDeleteResultDTO> deleteVehicle_exp(DeleteVehicleFromVcRTO rto) {
        System.out.println("删除布控车牌id：" + rto.getVcId());
        Integer affectedRows = ksvcServiceMaper.deleteVehicle(rto);
        ResultDTO<DbDeleteResultDTO> dto = new ResultDTO<>();
        DbDeleteResultDTO data = new DbDeleteResultDTO(affectedRows);
        dto.setData(data);
        return dto;
    }


}
