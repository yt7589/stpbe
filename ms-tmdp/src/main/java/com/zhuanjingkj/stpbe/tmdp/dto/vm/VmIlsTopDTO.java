package com.zhuanjingkj.stpbe.tmdp.dto.vm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

import java.util.List;

/**
 * 违章分布 =》 违章统计
 */
public class VmIlsTopDTO extends BaseDTO {
    @JSONField(name = "ilsAreaDTO")
    List<VmIlsTopAreaDTO> vmIlsTopAreaDTO;
    @JSONField(name = "ilsSiteDTO")
    List<VmIlsTopSiteDTO> vmIlsTopSiteDTO;

    public List<VmIlsTopAreaDTO> getIlsAreaDTO() {
        return vmIlsTopAreaDTO;
    }

    public void setIlsAreaDTO(List<VmIlsTopAreaDTO> vmIlsTopAreaDTO) {
        this.vmIlsTopAreaDTO = vmIlsTopAreaDTO;
    }

    public List<VmIlsTopSiteDTO> getIlsSiteDTO() {
        return vmIlsTopSiteDTO;
    }

    public void setIlsSiteDTO(List<VmIlsTopSiteDTO> vmIlsTopSiteDTO) {
        this.vmIlsTopSiteDTO = vmIlsTopSiteDTO;
    }
}
