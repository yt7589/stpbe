package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

public class KsSvsDTO extends BaseDTO {
    @JSONField(name = "htfs")
    private KsSvsHtfsDTO htfs; // 中间头部流量统计
    @JSONField(name = "ksvmc")
    private List<KsSvsKsvmcDTO> ksvmcs; // 左侧第一行：重点监控车辆车型组成
    @JSONField(name = "Ksvads")
    private List<KsSvsKsvadDTO> ksvads; // 左侧第二行：重点监控车辆区域分布
    @JSONField(name = "ksvrps")
    private List<KsSvsKsvrpDTO> ksvrps; // 左侧第三行：重点监控车辆实时图片
    @JSONField(name = "svtvs")
    private List<KsSvsSvtvDTO> svtvs; // 中间底部：交通违法信息
    @JSONField(name = "ksvtitfs")
    private List<KsSvsKsvtitfDTO> ksvtitfs; // 右侧第一行：本日重点监控车辆小时分布图

    public List<KsSvsKsvmcDTO> getKsvmcs() {
        return ksvmcs;
    }

    public void setKsvmcs(List<KsSvsKsvmcDTO> ksvmcs) {
        this.ksvmcs = ksvmcs;
    }

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

    public List<KsSvsKsvrpDTO> getKsvrps() {
        return ksvrps;
    }

    public void setKsvrps(List<KsSvsKsvrpDTO> ksvrps) {
        this.ksvrps = ksvrps;
    }

    public List<KsSvsSvtvDTO> getSvtvs() {
        return svtvs;
    }

    public void setSvtvs(List<KsSvsSvtvDTO> svtvs) {
        this.svtvs = svtvs;
    }

    public List<KsSvsKsvtitfDTO> getKsvtitfs() {
        return ksvtitfs;
    }

    public void setKsvtitfs(List<KsSvsKsvtitfDTO> ksvtitfs) {
        this.ksvtitfs = ksvtitfs;
    }
}
