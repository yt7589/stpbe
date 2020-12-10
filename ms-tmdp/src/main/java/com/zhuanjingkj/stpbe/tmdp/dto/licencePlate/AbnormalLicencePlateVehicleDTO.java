package com.zhuanjingkj.stpbe.tmdp.dto.licencePlate;

/**
 * author by guoqiang
 * date on 2020.12.09
 **/
public class AbnormalLicencePlateVehicleDTO {

    private String location;
    private String date;
    private String abnormalLicencePlateType;
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAbnormalLicencePlateType() {
        return abnormalLicencePlateType;
    }

    public void setAbnormalLicencePlateType(String abnormalLicencePlateType) {
        this.abnormalLicencePlateType = abnormalLicencePlateType;
    }
}
