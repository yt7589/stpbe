package com.zhuanjingkj.zjcbe.business.dto.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class VehicleAnalysisResponseDTO {

    @ApiModelProperty("图片编号")
    private String imageId;

    @ApiModelProperty("图片名称")
    private String imageName;

    @ApiModelProperty("图片URL地址")
    private String imageUrl;

    @ApiModelProperty("rtsp流序号Id")
    private int streamId;

    @ApiModelProperty("timeStamp")
    private long timeStamp;

    @ApiModelProperty("车辆识别结果")
    @JsonProperty("VEH")
    private List<VehicleAnalysisDetailDTO> veh;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<VehicleAnalysisDetailDTO> getVeh() {
        return veh;
    }

    public void setVeh(List<VehicleAnalysisDetailDTO> veh) {
        this.veh = veh;
    }

    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
