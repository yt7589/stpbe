package com.zhuanjingkj.stpbe.data.vo;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TvisJsonVO {
    private String tblName;
    private long tvisJsonId;
    private String occurTime;
    private long cameraId;
    private long streamId;
    private long pts;
    private String imageHash;
    private String jsonHash;

    public TvisJsonVO(String tblName, long tvisJsonId, String occurTime, long cameraId,
                      long streamId, long pts, String imageHash,
                      String jsonHash) {
        this.tblName = tblName;
        this.tvisJsonId = tvisJsonId;
        this.occurTime = occurTime;
        this.cameraId = cameraId;
        this.streamId = streamId;
        this.pts = pts;
        this.imageHash = imageHash;
        this.jsonHash = jsonHash;
    }

    public TvisJsonVO(long tvisJsonId, Timestamp occurTime, long cameraId, String imageHash, String jsonHash, long pts) {
        this.tvisJsonId = tvisJsonId;
        Date d = new Date();
        d.setTime(occurTime.getTime());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.occurTime = df.format(d);
        this.cameraId = cameraId;
        this.imageHash = imageHash;
        this.jsonHash = jsonHash;
        this.pts = pts;
    }

    public String getTblName() {
        return tblName;
    }

    public void setTblName(String tblName) {
        this.tblName = tblName;
    }

    public long getTvisJsonId() {
        return tvisJsonId;
    }

    public void setTvisJsonId(long tvisJsonId) {
        this.tvisJsonId = tvisJsonId;
    }

    public String getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(String occurTime) {
        this.occurTime = occurTime;
    }

    public long getCameraId() {
        return cameraId;
    }

    public void setCameraId(long cameraId) {
        this.cameraId = cameraId;
    }

    public long getStreamId() {
        return streamId;
    }

    public void setStreamId(long streamId) {
        this.streamId = streamId;
    }

    public long getPts() {
        return pts;
    }

    public void setPts(long pts) {
        this.pts = pts;
    }

    public String getImageHash() {
        return imageHash;
    }

    public void setImageHash(String imageHash) {
        this.imageHash = imageHash;
    }

    public String getJsonHash() {
        return jsonHash;
    }

    public void setJsonHash(String jsonHash) {
        this.jsonHash = jsonHash;
    }
}

