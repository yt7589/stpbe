package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.tmdp.dto.DkVttfDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkVttfSeriesDTO;

import java.util.List;

public interface IDkVttfService {
    public DkVttfDTO getDkVttf();

    public List<DkVttfSeriesDTO> getDkVttfSeriesDTOs_exp();
}
