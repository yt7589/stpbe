package com.zhuanjingkj.zjcbe.business.dto.dc;

import io.swagger.annotations.ApiModelProperty;

public class QueryImageRecgDatasItemDTO {
    @ApiModelProperty("图片编号")
    private String imageId;
    @ApiModelProperty("图片URL")
    private String imageUrl;
    @ApiModelProperty("上传日期")
    private String uploadTime;
    @ApiModelProperty("包含车辆数量")
    private int vehicleNum;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(int vehicleNum) {
        this.vehicleNum = vehicleNum;
    }
}
