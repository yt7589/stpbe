package com.zhuanjingkj.stpbe.data.entity;

/**
 * author by guoqiang
 * date on 2020.12.05
 **/
public class LicensePlateType {

    private Integer id;
    private String licensePlateTypeName;
    private String licensePlateTypeCode;
    private String licensePlateTypeDescribe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicensePlateTypeName() {
        return licensePlateTypeName;
    }

    public void setLicensePlateTypeName(String licensePlateTypeName) {
        this.licensePlateTypeName = licensePlateTypeName;
    }

    public String getLicensePlateTypeCode() {
        return licensePlateTypeCode;
    }

    public void setLicensePlateTypeCode(String licensePlateTypeCode) {
        this.licensePlateTypeCode = licensePlateTypeCode;
    }

    public String getLicensePlateTypeDescribe() {
        return licensePlateTypeDescribe;
    }

    public void setLicensePlateTypeDescribe(String licensePlateTypeDescribe) {
        this.licensePlateTypeDescribe = licensePlateTypeDescribe;
    }
}
