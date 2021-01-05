package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.tmdp.dto.DkVtpDTO;

import java.util.List;

public interface IDkVtpService {
    public DkVtpDTO getDkVtp();

    public List<DkVtpDTO> getDkVtpDTOs_exp();
}
