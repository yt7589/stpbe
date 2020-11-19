package com.zhuanjingkj.stpbe.tmdp.service.impl;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkVtieDTO;
import com.zhuanjingkj.stpbe.tmdp.service.IDkVtieService;
import org.springframework.stereotype.Service;

@Service
public class DkVtieService implements IDkVtieService {
    @Override
    public DkVtieDTO getDkVtie() {
        DkVtieDTO data = new DkVtieDTO();
        data.setInternalPercent(80);
        data.setExternalPercent(20);
        return data;
    }
}
