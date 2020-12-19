package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class KsSvsKsvtitfsDTO extends BaseDTO {
    @JSONField(name = "vtName")
    private String vtName;
    @JSONField(name = "titfs")
    private List<KsSvsKsvtitfDTO> titfs;

    public KsSvsKsvtitfsDTO(String vtName, List<KsSvsKsvtitfDTO> titfs) {
        this.vtName = vtName;
        this.titfs = titfs;
    }

    public String getVtName() {
        return vtName;
    }

    public void setVtName(String vtName) {
        this.vtName = vtName;
    }

    public List<KsSvsKsvtitfDTO> getTitfs() {
        return titfs;
    }

    public void setTitfs(List<KsSvsKsvtitfDTO> titfs) {
        this.titfs = titfs;
    }
}
