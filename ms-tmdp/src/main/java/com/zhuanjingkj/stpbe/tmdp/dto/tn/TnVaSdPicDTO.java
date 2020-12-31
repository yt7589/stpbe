package com.zhuanjingkj.stpbe.tmdp.dto.tn;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class TnVaSdPicDTO extends BaseDTO {
    @JSONField(name = "imageId")
    private long imageId;
    @JSONField(name = "tblUrl")
    private String tblUrl; //thumbnail 压缩图路径
    @JSONField(name = "picUrl")
    private String picUrl; //原图（放大图）
    @JSONField(name = "siteId")
    private long siteId; //摄像头点位
    @JSONField(name = "siteName")
    private String siteName; //摄像头名称
    @JSONField(name = "vModel")
    private String vModel; //车型
    @JSONField(name = "yModel")
    private String yModel; //年款
    @JSONField(name = "hphm")
    private String hphm; //车牌号码
    @JSONField(name = "ilsType")
    private String ilsType; //违章类型
    @JSONField(name = "time")
    private String time; //时间

    public TnVaSdPicDTO(long imageId, String tblUrl, String picUrl, long siteId, String siteName,
                        String vModel, String yModel, String hphm, String ilsType, String time) {
        this.imageId = imageId;
        this.tblUrl = tblUrl;
        this.picUrl = picUrl;
        this.siteId = siteId;
        this.siteName = siteName;
        this.vModel = vModel;
        this.yModel = yModel;
        this.hphm = hphm;
        this.ilsType = ilsType;
        this.time = time;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getTblUrl() {
        return tblUrl;
    }

    public void setTblUrl(String tblUrl) {
        this.tblUrl = tblUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getvModel() {
        return vModel;
    }

    public void setvModel(String vModel) {
        this.vModel = vModel;
    }

    public String getyModel() {
        return yModel;
    }

    public void setyModel(String yModel) {
        this.yModel = yModel;
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getIlsType() {
        return ilsType;
    }

    public void setIlsType(String ilsType) {
        this.ilsType = ilsType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
