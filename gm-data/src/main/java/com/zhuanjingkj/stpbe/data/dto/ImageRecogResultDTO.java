package com.zhuanjingkj.stpbe.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhuanjingkj.stpbe.data.vo.VehicleCxtzVo;

import java.util.List;

public class ImageRecogResultDTO extends BaseDTO {
    private String imageId;
    private String imageName;
    private String imageUrl;
    private int streamId;
    private long timeStamp;
    @JsonProperty("VEH")
    private List<VehicleCxtzVo> veh;

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

    public List<VehicleCxtzVo> getVeh() {
        return veh;
    }

    public void setVeh(List<VehicleCxtzVo> veh) {
        this.veh = veh;
    }
}
