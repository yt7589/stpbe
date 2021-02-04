package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DcHpMapper;
import com.zhuanjingkj.stpbe.common.mapper.DkRtvrMapper;
import com.zhuanjingkj.stpbe.common.net.IpfsClient;
import com.zhuanjingkj.stpbe.data.dto.DbQrsDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.DcHpDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcHpIlTrendDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcHpRgTrendDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDcHpService;
import com.zhuanjingkj.stpbe.tmdp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DcHpService implements IDcHpService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DcHpMapper dcHpMapper;

    @Autowired
    private DkRtvrMapper dkRtvrMapper;
    @Override
    public ResultDTO<DbQrsDTO> queryAllData_exp(int startIndex, int amount, Integer direction, String startTime,
                                                String endTime, String category, String vType, String ilType, String hphm, String vAddr) {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        if(direction == 0) {
            startIndex = (startIndex - amount * 2) < 0 ? 0 : (startIndex - amount * 2);
        }
        List<DcHpDTO> recs = dcHpMapper.getVehicleData(startIndex, amount, startTime, endTime, category, vType, ilType, hphm, vAddr);
        Integer count = dcHpMapper.getVehicleCount(startTime, endTime, category, vType, ilType, hphm, vAddr);
        if(recs != null && recs.size() > 0) {
            for(int i = 0; i < recs.size(); i++) {
                String tblName = recs.get(i).getTvisJsonTbl().replace("StpDb.", "");
                long jsonId = recs.get(i).getTvisJsonId();
                Map<String, Object> map = dkRtvrMapper.getImageHash(jsonId, tblName);
                if(map != null && map.size() > 0) {
                    recs.get(i).setImageUrl(IpfsClient.getIpfsUrl("" + map.get("image_hash")));
                }

            }
        }
        DbQrsDTO data = new DbQrsDTO(count,recs.size(),startIndex,amount,direction,recs);
//        List<DcHpDTO> recs = new ArrayList<>();
 //        for (int i=0; i<amount; i++) {
//            recs.add(new DcHpDTO(startIndex+i,"2020-12-29 14:50:03",
//                    "北京" + startIndex + "-" + i,"京A-"+ startIndex + "-" + i,
//                    "外埠","是","违章",
//                    "http://222.128.117.234:8090/cloud/images/a002.jpg"));
//        }
//        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }

    @Override
    public List<DcHpIlTrendDTO> getDit_exp() {
        List<DcHpIlTrendDTO> dit = new ArrayList<>();
        Map<String, Integer> res30Map = DateUtil.dayFor30Map(30, DateUtil.DTF_NYR);
        String endTime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String startTime = DateUtil.plusDaysForDate(endTime, -29);
        List<Map<String,Object>> recs = dcHpMapper.getVmDitCount(startTime, endTime);
        if(recs != null && recs.size() > 0)  {
            for(int i = 0; i < recs.size(); i++) {
                res30Map.put("" + recs.get(i).get("createTime"), Integer.parseInt("" + recs.get(i).get("count")));
            }
        }
        int count = 1;
        for(String key : res30Map.keySet()) {
            dit.add(new DcHpIlTrendDTO("" + count, res30Map.get(key)));
            count++;
        }
        return dit;
    }

    @Override
    public List<DcHpRgTrendDTO> getDrt_exp() {
        List<DcHpRgTrendDTO> drt = new ArrayList<>();
        Map<String, Integer> res30Map = DateUtil.dayFor30Map(30, DateUtil.DTF_NYR);
        String endTime = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String startTime = DateUtil.plusDaysForDate(endTime, -29);
        List<Map<String, Object>> recs = dcHpMapper.getVmDrtCount(startTime, endTime);
        if(recs != null && recs.size() > 0) {
            for(int i = 0; i < recs.size(); i++) {
                res30Map.put("" + recs.get(i).get("createTime"), Integer.parseInt("" + recs.get(i).get("count")));
            }
        }
        int count = 1;
        for(String key : res30Map.keySet()) {
            drt.add(new DcHpRgTrendDTO("" + count, res30Map.get(key)));
            count++;
        }
        return drt;
    }
}
