package com.zhuanjingkj.stpbe.data.entity;

/**
 * author by guoqiang
 * date on 2020.12.15
 **/
public class Region {

    private Integer id;
    private String regionName;
    private Integer parentId;
    private Integer level;
    private Integer isEmphasisRegion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIsEmphasisRegion() {
        return isEmphasisRegion;
    }

    public void setIsEmphasisRegion(Integer isEmphasisRegion) {
        this.isEmphasisRegion = isEmphasisRegion;
    }
}
