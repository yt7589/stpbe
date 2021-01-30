package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.TptfMapper;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpTfDTO;
import com.zhuanjingkj.stpbe.data.dto.TpTfSiteDTO;
import com.zhuanjingkj.stpbe.data.dto.TpTfStDTO;
import com.zhuanjingkj.stpbe.tmdp.service.ITptfService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TptfService implements ITptfService {

    @Autowired
    private TptfMapper tptfMapper;

    @Override
    public ResultDTO<TpTfDTO> queryTrafficForecast_exp(String date, String time) {
        ResultDTO<TpTfDTO> dto = new ResultDTO<TpTfDTO>();
        TpTfDTO tfDTO = new TpTfDTO();
        String pTime = date + " " + time;
        String queryTime = DateUtil.plus7Days(pTime,-7);
        Integer count = tptfMapper.getVehicle4TimeCount(queryTime);
        List<TpTfStDTO> tfst = tptfMapper.getVehicle4TimeSite(queryTime);
        List<TpTfSiteDTO> tfs = tptfMapper.getVehicle4TimeMap(queryTime);
//        List<TpTfStDTO> tfst = new ArrayList<>();
//        tfst.add(new TpTfStDTO("上地三街129号", 203));
//        tfst.add(new TpTfStDTO("上地四街129号", 303));
//        tfst.add(new TpTfStDTO("上地五街129号", 403));
//        tfst.add(new TpTfStDTO("上地六街129号", 503));
//        tfst.add(new TpTfStDTO("上地七街129号", 603));
//        tfst.add(new TpTfStDTO("上地八街129号", 703));
//        tfst.add(new TpTfStDTO("上地九街129号", 803));
//        tfst.add(new TpTfStDTO("上地十街129号", 903));
//        tfst.add(new TpTfStDTO("上地二街129号", 100));
//        tfst.add(new TpTfStDTO("上地西路129号", 110));
//        List<TpTfSiteDTO> tfs = new ArrayList<>();
//        tfs.add(new TpTfSiteDTO(104, "上地七街", 116.098754, 40.1568921));
//        tfs.add(new TpTfSiteDTO(104, "上地东路", 116.198754, 40.2568921));
//        tfs.add(new TpTfSiteDTO(104, "上地西路", 116.298754, 40.3568921));
//        tfs.add(new TpTfSiteDTO(104, "上地二街", 116.398754, 40.4568921));
//        tfs.add(new TpTfSiteDTO(104, "上地一街", 116.498754, 40.5568921));
//        tfs.add(new TpTfSiteDTO(104, "上地四街", 116.598754, 40.6568921));
//        tfs.add(new TpTfSiteDTO(104, "上地五街", 116.698754, 40.7568921));
//        tfs.add(new TpTfSiteDTO(104, "上地六街", 116.798754, 40.8568921));
//        tfs.add(new TpTfSiteDTO(104, "上地七街", 116.898754, 40.9568921));
//        tfs.add(new TpTfSiteDTO(104, "上地八街", 116.998754, 40.0568921));
        tfDTO.setTfst(tfst);
        tfDTO.setTfs(tfs);
        tfDTO.settVehicle(count);
        dto.setData(tfDTO);
        return dto;
    }
}
