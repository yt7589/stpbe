package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.common.mapper.DcStMapper;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcStSysDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.dc.DcStTodayDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDcStService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class DcStService implements IDcStService {

    @Autowired
    private DcStMapper dcStMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public DcStSysDTO getSys_exp() {
        Integer device = dcStMapper.getDeviceCount(); //设备总数
        Integer control = dcStMapper.getVehicleControl(); //布控车辆
        Integer ils = dcStMapper.getIlCount(); //违章总数
        Integer alerts = Integer.parseInt(redisTemplate.opsForValue().get("dcst_vehicle_alerts") == null ? "0" : "" + redisTemplate.opsForValue().get("dcst_vehicle_alerts"));
        Integer keyVehicle = Integer.parseInt(redisTemplate.opsForValue().get("dcst_key_vehicle") == null ? "0" : "" + redisTemplate.opsForValue().get("dcst_key_vehicle") );
        Integer truck = Integer.parseInt(redisTemplate.opsForValue().get("dcst_key_truck") == null ? "0" : "" + redisTemplate.opsForValue().get("dcst_key_truck") );
        DcStSysDTO sysDTO = new DcStSysDTO(device,control,alerts,keyVehicle,ils,truck);
        return sysDTO;
    }

    @Override
    public DcStTodayDTO getStToday_exp() {
        Integer vehicle = Integer.parseInt(redisTemplate.opsForValue().get("dcst_vehicle_total") == null ? "0" : "" + redisTemplate.opsForValue().get("dcst_vehicle_total"));
        Integer vc = Integer.parseInt(redisTemplate.opsForValue().get("dcst_category_0_total") == null ? "0" : "" + redisTemplate.opsForValue().get("dcst_category_0_total"));
        Integer vt = Integer.parseInt(redisTemplate.opsForValue().get("dcst_category_1_total") == null ? "0" : "" + redisTemplate.opsForValue().get("dcst_category_1_total"));
        Double vcact = 100.0 * vc /vehicle;
        Integer device = dcStMapper.getDeviceCount(); //设备总数
        Integer dv = dcStMapper.getDvCount(); //异常设备
        Double dvOnline = 100.0 * dv / device;
        DcStTodayDTO dcStTodayDTO = new DcStTodayDTO(vehicle,vc,vt,vcact.intValue(),dvOnline.intValue());
        return dcStTodayDTO;
    }

}
