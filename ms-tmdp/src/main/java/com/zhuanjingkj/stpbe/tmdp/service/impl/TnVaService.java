package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.TnVaMapper;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.TnVaDeviceDeployDTO;
import com.zhuanjingkj.stpbe.data.dto.TnVaSdInfoDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVaDeviceDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.tn.TnVaSiteInfoDTO;
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
    public ResultDTO<DbQrsDTO> queryDeviceDeploy_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        List<TnVaDeviceDeployDTO> recs = tnVaMapper.getSite();
        Integer count = tnVaMapper.getSiteCount();
        DbQrsDTO data = new DbQrsDTO(count,recs.size(), 0, 0, 0,recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<TnVaDeviceDTO> queryDevice_exp() {
        ResultDTO<TnVaDeviceDTO> dto = new ResultDTO<>();
        List<Map<String, Object>> recs = tnVaMapper.getDeviceCount();
        TnVaDeviceDTO data = new TnVaDeviceDTO(0, 0,0, 0);
        if(recs != null && recs.size() > 0) {
            for(int i = 0; i < recs.size(); i++) {
                if("1".equals("" + recs.get(i).get("camera_type_id")) && "1".equals("" + recs.get(i).get("status"))) {
                    data.setCamera(Integer.parseInt(recs.get(i).get("count") == null ? "0" : "" + recs.get(i).get("count")));
                } else if("1".equals("" + recs.get(i).get("camera_type_id")) && "0".equals("" + recs.get(i).get("status"))) {
                    data.setAbnormal_camera(Integer.parseInt(recs.get(i).get("count") == null ? "0" : "" + recs.get(i).get("count")));
                } else if("2".equals("" + recs.get(i).get("camera_type_id")) && "1".equals("" + recs.get(i).get("status"))) {
                    data.setSnapshot(Integer.parseInt(recs.get(i).get("count") == null ? "0" : "" + recs.get(i).get("count")));
                } else if("2".equals("" + recs.get(i).get("camera_type_id")) && "0".equals("" + recs.get(i).get("status"))) {
                    data.setAbnormal_snapshot(Integer.parseInt(recs.get(i).get("count") == null ? "0" : "" + recs.get(i).get("count")));
                }
            }
        }
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<TnVaSiteInfoDTO> querySdInfo_exp(Integer startIndex, Integer amount, Integer direction, long siteId) {
        ResultDTO<TnVaSiteInfoDTO> dto = new ResultDTO<>();
        TnVaSiteInfoDTO ts = new TnVaSiteInfoDTO();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<TnVaSdInfoDTO> recs = tnVaMapper.getSiteInfo(startIndex, amount, siteId);
        ts.setRecs(recs);
        List<Map<String, Object>> devCount = tnVaMapper.getDevCount(siteId);
        if(devCount != null && devCount.size() > 0) {
            for(int i = 0; i < devCount.size(); i++) {
                if("1".equals("" + devCount.get(i).get("camera_type_id"))) {
                    ts.setCamera(Integer.parseInt(devCount.get(i).get("count") == null ? "0" : "" + devCount.get(i).get("count")));
                } else if("2".equals("" + devCount.get(i).get("camera_type_id"))) {
                    ts.setSnapshot(Integer.parseInt(devCount.get(i).get("count") == null ? "0" : "" + devCount.get(i).get("count")));
                }
            }
        }
        dto.setData(ts);
        return dto;
    }
}
