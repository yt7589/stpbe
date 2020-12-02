package com.zhuanjingkj.stpbe.data.rto.vehicle;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

import java.util.List;

/**
 * author by guoqiang
 * date on 2020.12.01
 **/
public class ResultRTO extends BaseRTO {

    /**
     * 车辆记录数
     */
    @JSONField(name = "JLS")
    private String recordNumber;

    /**
     * 流ID
     */
    @JSONField(name = "StreamID")
    private String streamId;

    @JSONField(name = "CODE")
    private String code;

    /**
     * 过车序号
     */
    @JSONField(name = "GCXH")
    private String vehicleNumber;

    /**
     * 时间戳
     */
    @JSONField(name = "TimeStamp")
    private Long timeStamp;

    /**
     * 识别结果
     */
    @JSONField(name = "VEH")
    private List<VehicleInfoRTO> vehicleInfoList;

    /**
     * 图片url
     */
    @JSONField(name = "ImageUrl")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getStreamId() {
        return streamId;
    }

    public void setStreamId(String streamId) {
        this.streamId = streamId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<VehicleInfoRTO> getVehicleInfoList() {
        return vehicleInfoList;
    }

    public void setVehicleInfoList(List<VehicleInfoRTO> vehicleInfoList) {
        this.vehicleInfoList = vehicleInfoList;
    }
}
