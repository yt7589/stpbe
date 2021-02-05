package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.TnVaMapper;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.TnVaDeviceDeployDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVaDeviceDTO;
import com.zhuanjingkj.stpbe.tmdp.service.ITnVaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TnVaService implements ITnVaService {

    @Autowired
    private TnVaMapper tnVaMapper;

    @Override
    public ResultDTO<DbQrsDTO> queryDeviceDeploy_exp(Integer startIndex, Integer amount, Integer direction) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<TnVaDeviceDeployDTO> recs = tnVaMapper.getSite();
        Integer count = tnVaMapper.getSiteCount();
        DbQrsDTO data = new DbQrsDTO(count,recs.size(), startIndex, amount, direction,recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<TnVaDeviceDTO> queryDevice_exp() {
        ResultDTO<TnVaDeviceDTO> dto = new ResultDTO<>();
        List<Map<String, Object>> recs = tnVaMapper.getDeviceCount();
        TnVaDeviceDTO data = new TnVaDeviceDTO(18000, 20,19000, 10);
        dto.setData(data);
        return dto;
    }
}
