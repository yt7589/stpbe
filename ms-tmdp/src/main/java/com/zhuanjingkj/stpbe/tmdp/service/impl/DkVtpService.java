package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.tmdp.dto.DkVtpDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDkVtpService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DkVtpService implements IDkVtpService {
    @Override
    public DkVtpDTO getDkVtp() {
        Map<String,  Integer> percents = new HashMap<>();
        percents.put("小汽车", 10);
        percents.put("SUV", 20);
        percents.put("MPV", 30);
        percents.put("面包车", 40);
        percents.put("厢式货车", 50);
        percents.put("普通货车", 60);
        DkVtpDTO dkVtp = new DkVtpDTO();
        dkVtp.setPercents(percents);
        return dkVtp;
    }
}
