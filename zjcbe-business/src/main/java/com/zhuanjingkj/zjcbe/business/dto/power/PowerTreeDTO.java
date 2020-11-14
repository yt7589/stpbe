package com.zhuanjingkj.zjcbe.business.dto.power;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PowerTreeDTO {

    @ApiModelProperty("权限菜单Id")
    private int powerId;
    @ApiModelProperty("权限菜单名称")
    private String powerName;
    @ApiModelProperty("权限路径")
    private String powerPath;
    @ApiModelProperty("图标")
    private String icon;
    @ApiModelProperty("权限类型（1-模块，2-页面）")
    private int powerType;
    @ApiModelProperty("父节点Id")
    private int parentId;
    @ApiModelProperty("子节点")
    private List<PowerTreeDTO> childs;

    public int getPowerId() {
        return powerId;
    }

    public void setPowerId(int powerId) {
        this.powerId = powerId;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getPowerPath() {
        return powerPath;
    }

    public void setPowerPath(String powerPath) {
        this.powerPath = powerPath;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getPowerType() {
        return powerType;
    }

    public void setPowerType(int powerType) {
        this.powerType = powerType;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<PowerTreeDTO> getChilds() {
        return childs;
    }

    public void setChilds(List<PowerTreeDTO> childs) {
        this.childs = childs;
    }
}
