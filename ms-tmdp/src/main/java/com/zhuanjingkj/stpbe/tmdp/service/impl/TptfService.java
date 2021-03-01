package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.TptfMapper;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tp.TpTfDTO;
import com.zhuanjingkj.stpbe.data.dto.TpTfSiteDTO;
import com.zhuanjingkj.stpbe.data.dto.TpTfStDTO;
import com.zhuanjingkj.stpbe.tmdp.service.ITptfService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TptfService implements ITptfService {

    @Autowired
    private TptfMapper tptfMapper;

    @Autowired
    private DcStService dcStService;

    @Override
    public ResultDTO<TpTfDTO> queryTrafficForecast_exp(String date, String time) {
        ResultDTO<TpTfDTO> dto = new ResultDTO<TpTfDTO>();
        TpTfDTO tfDTO = new TpTfDTO();
        String pTime = date + " " + time;
        String queryTime = DateUtil.plus7Days(pTime,-7, DateUtil.DTF_HM);
        Integer count = tptfMapper.getVehicle4TimeCount(queryTime);
        List<TpTfStDTO> tfst = tptfMapper.getVehicle4TimeSite(queryTime);
        List<TpTfSiteDTO> tfs = tptfMapper.getVehicle4TimeMap(queryTime);
        Map<String, Object> siteNameMap = dcStService.siteNameMap;
        StringBuffer sb = null;
        if (tfst == null) {
            for (String key : siteNameMap.keySet()) {
                sb = new StringBuffer();
                if (!sb.toString().contains("" + siteNameMap.get(key))) {
                    tfst.add(new TpTfStDTO("" + siteNameMap.get(key), 0));
                    sb.append(siteNameMap.get(key));
                }
                if (tfst.size() == 10) {
                    break;
                }
            }
        } else if (tfst != null && tfst.size() < 10) {
            sb = new StringBuffer();
            if (tfst.size() > 0) {
                for (int i = 0; i < tfst.size(); i++) {
                    sb.append(tfst.get(i).getName());
                }
            }

            for (String key : siteNameMap.keySet()) {
                if (!sb.toString().contains("" + siteNameMap.get(key))) {
                    tfst.add(new TpTfStDTO("" + siteNameMap.get(key), 0));
                    sb.append(siteNameMap.get(key));
                }
                if (tfst.size() == 10) {
                    break;
                }
            }
        }
        tfDTO.setTfst(tfst);
        tfDTO.setTfs(tfs);
        tfDTO.settVehicle(count);
        dto.setData(tfDTO);
        return dto;
    }
}
