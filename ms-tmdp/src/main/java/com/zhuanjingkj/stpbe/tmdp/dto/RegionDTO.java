package com.zhuanjingkj.stpbe.tmdp.dto;

import com.netflix.discovery.DNSBasedAzToRegionMapper;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on 2020.12.08
 **/
public class RegionDTO extends BaseDTO {
    private Integer regionId;
    private String regionName;
    private Integer regionTrafficViolationNum;

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getRegionTrafficViolationNum() {
        return regionTrafficViolationNum;
    }

    public void setRegionTrafficViolationNum(Integer regionTrafficViolationNum) {
        this.regionTrafficViolationNum = regionTrafficViolationNum;
    }
}
