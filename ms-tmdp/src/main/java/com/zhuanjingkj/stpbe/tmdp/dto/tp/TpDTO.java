package com.zhuanjingkj.stpbe.tmdp.dto.tp;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.TpTfpsDTO;

import java.util.List;

public class TpDTO extends BaseDTO {
    @JSONField(name = "tfps")
    List<TpTfpsDTO> tfps;

    public List<TpTfpsDTO> getTfps() {
        return tfps;
    }

    public void setTfps(List<TpTfpsDTO> tfps) {
        this.tfps = tfps;
    }
}
