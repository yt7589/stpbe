package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class KsSvsDTO extends BaseDTO {
    @JSONField(name = "htfs")
    private KsSvsHtfsDTO htfs; // 中间头部流量统计
    @JSONField(name = "Ksvads")
    private List<KsSvsKsvadDTO> ksvads; // 左侧第二行：重点监控车辆区域分布

    public List<KsSvsKsvadDTO> getKsvads() {
        return ksvads;
    }

    public void setKsvads(List<KsSvsKsvadDTO> ksvads) {
        this.ksvads = ksvads;
    }

    public KsSvsHtfsDTO getHtfs() {
        return htfs;
    }

    public void setHtfs(KsSvsHtfsDTO htfs) {
        this.htfs = htfs;
    }
}