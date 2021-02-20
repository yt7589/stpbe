package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.KsVcMapper;
import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.data.rto.ks.AddVehicleToVcRTO;
import com.zhuanjingkj.stpbe.data.rto.ks.DeleteVehicleFromVcRTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsVcLsvsDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.ks.KsVcSfvsDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IKsVcService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KsVcService implements IKsVcService {

    @Autowired
    private KsVcMapper ksvcServiceMaper;

    @Autowired
    private RedisTemplate redisTemplate;

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

    @Override
    public ResultDTO<DbQrsDTO> queryVcIllLsvs_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        List<KsVcLsvsDTO> recs = new ArrayList<>();
        List<String> ill = redisTemplate.opsForList().range("ks_vs_ill_list", 0, 3);
        if(ill != null && ill.size() > 0) {
            for(int i = 0; i < ill.size(); i++) {
                String val = ill.get(i);
                if(StringUtils.isNotBlank(val) && val.split("\\|").length == 2) {
                    String hphm = val.split("\\|")[0];
                    String cameraId = val.split("\\|")[1];
                    KsVcLsvsDTO illLsvs = new KsVcLsvsDTO(0,0,101,"" + KsAsService.areaMap.get(cameraId),
                            "" + redisTemplate.opsForHash().get("ks_vs_ill_time", val),hphm,Integer.parseInt("" + (redisTemplate.opsForHash().get("ks_vs_ill_total", val) == null ? "0" : redisTemplate.opsForHash().get("ks_vs_ill_total", val))));
                    recs.add(illLsvs);
                    if(recs.size() == 3) {
                        break;
                    }
                }
            }
        }
        DbQrsDTO data = new DbQrsDTO(4,4,0,4,1,recs);
//        recs.add(new KsVcLsvsDTO(0,0,101,"北京市海淀区西二旗街道19号","2020-12-21 18:02:57","赣Q817S2",1));
//        recs.add(new KsVcLsvsDTO(0,0,101,"北京市海淀区上地街道39号","2020-12-22 18:02:57","豫KL9687",2));
//        recs.add(new KsVcLsvsDTO(0,0,101,"北京市海淀区西直门街道29号","2020-12-23 18:02:57","豫Q817S2",3));
//        recs.add(new KsVcLsvsDTO(0,0,101,"北京市海淀区知春路街道109号","2020-12-29 18:02:57","赣KL9687",4));
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbQrsDTO> queryVcDynLsvs_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        List<KsVcLsvsDTO> recs = new ArrayList<>();
        List<String> dyn = redisTemplate.opsForList().range("ks_vs_dyn_list", 0, 10);
        if(dyn != null && dyn.size() > 0) {
            for(int i = 0; i < dyn.size(); i++) {
                String val = dyn.get(i);
                if(StringUtils.isNotBlank(val) && val.split("\\|").length == 2) {
                    String hphm = val.split("\\|")[0];
                    String cameraId = val.split("\\|")[1];
                    if(StringUtils.isBlank(hphm) || StringUtils.isBlank(cameraId) ) {
                        continue;
                    }
                    KsVcLsvsDTO illLsvs = new KsVcLsvsDTO(0,0,101,"" + KsAsService.areaMap.get(cameraId),
                            "" + redisTemplate.opsForHash().get("ks_vs_dyn_time", val),hphm,Integer.parseInt("" + redisTemplate.opsForHash().get("ks_vs_dyn_total", val)));
                    recs.add(illLsvs);
                    if(recs.size() == 3) {
                        break;
                    }
                }
            }
        }
        DbQrsDTO data = new DbQrsDTO(4,4,0,4,1,recs);
//        recs.add(new KsVcLsvsDTO(0,0,101,"北京市海淀区西二旗街道19号","2020-12-21 18:02:57","赣Q817S2",1));
//        recs.add(new KsVcLsvsDTO(0,0,101,"北京市海淀区上地街道39号","2020-12-22 18:02:57","豫KL9687",2));
//        recs.add(new KsVcLsvsDTO(0,0,101,"北京市海淀区西直门街道29号","2020-12-23 18:02:57","豫Q817S2",3));
//        recs.add(new KsVcLsvsDTO(0,0,101,"北京市海淀区知春路街道109号","2020-12-29 18:02:57","赣KL9687",4));
        dto.setData(data);
        return dto;
    }

    @Override
    public ResultDTO<DbQrsDTO> queryVcSfvs_exp() {
        ResultDTO<DbQrsDTO> dto = new ResultDTO<>();
        List<KsVcSfvsDTO> recs = new ArrayList<>();
        List<String> ill = redisTemplate.opsForList().range("ks_vs_ill_list", 0, 3);
        if(ill != null && ill.size() > 0) {
            for(int i = 0; i < ill.size(); i++) {
                String val = ill.get(i);
                String hphm = val.split("\\|")[0];
                String cameraId = val.split("\\|")[1];
                String coordinate = "" + KsAsService.areaSiteMap.get(cameraId);
                if(StringUtils.isBlank(hphm) || StringUtils.isBlank(cameraId)
                        || StringUtils.isBlank(coordinate)
                        || "null".equals(coordinate)) {
                    continue;
                }
                KsVcSfvsDTO illLsvs = new KsVcSfvsDTO(0,"" + KsAsService.areaMap.get(cameraId),Double.parseDouble("" + coordinate.split("\\|")[0]), Double.parseDouble("" + coordinate.split("\\|")[1]),
                        Integer.parseInt("" + redisTemplate.opsForHash().get("ks_vs_ill_total", val)), hphm);
                recs.add(illLsvs);
            }
        }
        DbQrsDTO data = new DbQrsDTO(4,4,0,4,1,recs);
//        recs.add(new KsVcSfvsDTO(101, "北京市海淀区西二旗街道19号",116.0185,40.0495, 1,"鲁KL9687"));
//        recs.add(new KsVcSfvsDTO(101, "北京市海淀区上地街道39号",116.0285,40.1495, 2,"贵Q817S2"));
//        recs.add(new KsVcSfvsDTO(101, "北京市海淀区西直门街道29号",116.0385,40.24295, 4,"赣Q817S2"));
//        recs.add(new KsVcSfvsDTO(101, "北京市海淀区知春路街道109号",116.0485,40.3495, 5,"赣Q817S2"));
//        recs.add(new KsVcSfvsDTO(101, "北京市朝阳区东湖区99号",116.0585,40.4495, 6,"沪KL9687"));
//        recs.add(new KsVcSfvsDTO(101, "北京市昌平区北七家街道21号",116.0685,40.5495, 9,"蒙Q817S2"));
//        recs.add(new KsVcSfvsDTO(101, "北京市望京街道59号",116.0785,40.6495, 3,"蒙Q817S2"));
//        recs.add(new KsVcSfvsDTO(101, "北京市海淀区回龙观39号",116.0885,40.7495, 6,"赣Q817S2"));
//        data.setRecs(recs);
        dto.setData(data);
        return dto;
    }


}
