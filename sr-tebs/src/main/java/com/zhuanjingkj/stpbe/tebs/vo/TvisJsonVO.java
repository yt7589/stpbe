package com.zhuanjingkj.stpbe.tebs.vo;

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
        this.streamId = streamId;
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
