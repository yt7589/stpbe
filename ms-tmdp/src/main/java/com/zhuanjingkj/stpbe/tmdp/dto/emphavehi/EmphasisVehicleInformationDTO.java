package com.zhuanjingkj.stpbe.tmdp.dto.emphavehi;

import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * author by guoqiang
 * date on 2020.11.23
 **/
public class EmphasisVehicleInformationDTO extends BaseDTO {

    /**
     * 重点车辆信息ID
     */
    private Integer id;

    /**
     * 重点车辆类型
     */
    private String emphasisVehicleTypeName;

    /**
     * 时间
     */
    private String date;

    /**
     * 重点车辆数量
     */
    private Integer emphasisVehicleNum;

    /**
     * 设备点位
     */
    private String deviceSiteName;

    /**
     * 区域名称
     */
    private String regionName;

    /**
     * 重点车辆占比
     */
    private String percentage;

    /**
     * 点位ID
     */
    private Integer siteId;

    /**
     * 点位名称
     */
    private String siteName;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmphasisVehicleTypeName() {
        return emphasisVehicleTypeName;
    }

    public void setEmphasisVehicleTypeName(String emphasisVehicleTypeName) {
        this.emphasisVehicleTypeName = emphasisVehicleTypeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getEmphasisVehicleNum() {
        return emphasisVehicleNum;
    }

    public void setEmphasisVehicleNum(Integer emphasisVehicleNum) {
        this.emphasisVehicleNum = emphasisVehicleNum;
    }

    public String getDeviceSiteName() {
        return deviceSiteName;
    }

    public void setDeviceSiteName(String deviceSiteName) {
        this.deviceSiteName = deviceSiteName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
