package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.TpMapper;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpDTO;
import com.zhuanjingkj.stpbe.data.dto.TpTfpsDTO;
import com.zhuanjingkj.stpbe.tmdp.service.ITpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TpService implements ITpService {

    @Autowired
    private TpMapper tpMapper;

    @Override
    public ResultDTO<TpDTO> queryTrafficPrognosis_exp(String startTime, String endTime, String hphm) {
        ResultDTO<TpDTO> dto = new ResultDTO<TpDTO>();
        List<TpTfpsDTO> tfps = tpMapper.getTrafficPrognosis(startTime, endTime, hphm);
        TpDTO td = new TpDTO();
//        List<TpTfpsDTO> tfps = new ArrayList<>();
//        tfps.add(new TpTfpsDTO(104, "上地七街", 116.08901, 40.05698));
//        tfps.add(new TpTfpsDTO(104, "上地东路", 116.18901, 40.15698));
//        tfps.add(new TpTfpsDTO(104, "上地西路", 116.28901, 40.25698));
//        tfps.add(new TpTfpsDTO(104, "上地二街", 116.38901, 40.35698));
//        tfps.add(new TpTfpsDTO(104, "上地一街", 116.48901, 40.45698));
        td.setTfps(tfps);
        dto.setData(td);
        return dto;
    }
}
