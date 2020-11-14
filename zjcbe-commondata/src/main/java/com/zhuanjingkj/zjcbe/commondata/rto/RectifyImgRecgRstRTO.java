package com.zhuanjingkj.zjcbe.commondata.rto;

public class RectifyImgRecgRstRTO extends BaseRTO{
    protected String imageId;
    protected String userJosn;
    protected String userNotes;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getUserJosn() {
        return userJosn;
    }

    public void setUserJosn(String userJosn) {
        this.userJosn = userJosn;
    }

    public String getUserNotes() {
        return userNotes;
    }

    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }
}
