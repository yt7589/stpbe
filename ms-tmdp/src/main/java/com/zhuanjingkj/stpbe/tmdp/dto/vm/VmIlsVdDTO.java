package com.zhuanjingkj.stpbe.tmdp.dto.vm;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

/**
 * 违章监管 =》车辆违章详情
 * 主驾驶 Master Driver
 * 副驾驶 copilot
 * 摩托车 Motorcycles
 *
 */
public class VmIlsVdDTO extends BaseDTO {
    @JSONField(name = "imageId")
    private long imageId; //违章照片id
    @JSONField(name = "imageUrl")
    private String imageUrl; //违章照片
    @JSONField(name = "ilsTime")
    private String ilsTime; //违章时间
    @JSONField(name = "ilsAddr")
    private String ilsAddr; //违章地点
    @JSONField(name = "category")
    private String category; //车辆类型
    @JSONField(name = "hphm")
    private String hphm; //车牌号码
    @JSONField(name = "ilsType")
    private String ilsType; //违章类型
    @JSONField(name = "vlType")
    private String vlType; //车辆类型> 大类
    @JSONField(name = "vsType")
    private String vsType; //车辆类型> 小类
    @JSONField(name = "direction")
    private String direction; //方位
    @JSONField(name = "md_isPhone")
    private Integer md_isPhone; //0:主驾驶未打电话 1:主驾驶在打电话
    @JSONField(name = "md_isWPhone")
    private Integer md_isWPhone; //0:未看手机 1:看手机
    @JSONField(name = "md_isSafetyBelt")
    private Integer md_isSafetyBelt; //0:未系安全带 1:系安全带
    @JSONField(name = "md_isSmoke")
    private Integer md_isSmoke; //0:未吸烟 1:正在吸烟
    @JSONField(name = "md_isSunVisor")
    private Integer md_isSunVisor; //0:未打开遮阳板 1:遮阳板打开
    @JSONField(name = "ct_isSafetyBelt")
    private Integer ct_isSafetyBelt; //0:未系安全带 1:系安全带
    @JSONField(name = "ct_isSunVisor")
    private Integer ct_isSunVisor; //0:未打开遮阳板 1:遮阳板打开
    @JSONField(name = "mc_isHelmet")
    private Integer mc_isHelmet; //0:未带头盔 1:已带头盔
    @JSONField(name = "color")
    private String color; //车体颜色
    @JSONField(name = "logo")
    private String logo; //车标
    @JSONField(name = "vModel")
    private String vModel; //车型
    @JSONField(name = "yModel")
    private String yModel; //年款
    @JSONField(name = "rlblt")
    private double rlblt; //可信度
    @JSONField(name = "hphm_state")
    private Integer hphm_state; //0:无车牌 1:有车牌
    @JSONField(name = "hphm_color")
    private String hphm_color; //车牌颜色
    @JSONField(name = "word_Style")
    private String word_Style; //车牌字体样式
    @JSONField(name = "hphm_type")
    private String hphm_type; //车牌种类
    @JSONField(name = "hphm_tps")
    private Integer hphm_tps; //临时车牌状态 0:无 1:有
    @JSONField(name = "hphm_rlblt")
    private double hphm_rlblt; //车牌可信度
    @JSONField(name = "ehphm_rlblt")
    private String ehphm_rlblt; //每位号牌可信度

    public VmIlsVdDTO(long imageId, String imageUrl, String ilsTime, String ilsAddr, String category,
                      String hphm, String ilsType, String vlType, String vsType, String direction,
                      Integer md_isPhone, Integer md_isWPhone, Integer md_isSafetyBelt, Integer md_isSmoke,
                      Integer md_isSunVisor, Integer ct_isSafetyBelt, Integer ct_isSunVisor,
                      Integer mc_isHelmet, String color, String logo, String vModel, String yModel,
                      double rlblt, Integer hphm_state, String hphm_color, String word_Style, String hphm_type,
                      Integer hphm_tps, double hphm_rlblt, String ehphm_rlblt) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
        this.ilsTime = ilsTime;
        this.ilsAddr = ilsAddr;
        this.category = category;
        this.hphm = hphm;
        this.ilsType = ilsType;
        this.vlType = vlType;
        this.vsType = vsType;
        this.direction = direction;
        this.md_isPhone = md_isPhone;
        this.md_isWPhone = md_isWPhone;
        this.md_isSafetyBelt = md_isSafetyBelt;
        this.md_isSmoke = md_isSmoke;
        this.md_isSunVisor = md_isSunVisor;
        this.ct_isSafetyBelt = ct_isSafetyBelt;
        this.ct_isSunVisor = ct_isSunVisor;
        this.mc_isHelmet = mc_isHelmet;
        this.color = color;
        this.logo = logo;
        this.vModel = vModel;
        this.yModel = yModel;
        this.rlblt = rlblt;
        this.hphm_state = hphm_state;
        this.hphm_color = hphm_color;
        this.word_Style = word_Style;
        this.hphm_type = hphm_type;
        this.hphm_tps = hphm_tps;
        this.hphm_rlblt = hphm_rlblt;
        this.ehphm_rlblt = ehphm_rlblt;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIlsTime() {
        return ilsTime;
    }

    public void setIlsTime(String ilsTime) {
        this.ilsTime = ilsTime;
    }

    public String getIlsAddr() {
        return ilsAddr;
    }

    public void setIlsAddr(String ilsAddr) {
        this.ilsAddr = ilsAddr;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getVlType() {
        return vlType;
    }

    public void setVlType(String vlType) {
        this.vlType = vlType;
    }

    public String getVsType() {
        return vsType;
    }

    public void setVsType(String vsType) {
        this.vsType = vsType;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getMd_isPhone() {
        return md_isPhone;
    }

    public void setMd_isPhone(Integer md_isPhone) {
        this.md_isPhone = md_isPhone;
    }

    public Integer getMd_isWPhone() {
        return md_isWPhone;
    }

    public void setMd_isWPhone(Integer md_isWPhone) {
        this.md_isWPhone = md_isWPhone;
    }

    public Integer getMd_isSafetyBelt() {
        return md_isSafetyBelt;
    }

    public void setMd_isSafetyBelt(Integer md_isSafetyBelt) {
        this.md_isSafetyBelt = md_isSafetyBelt;
    }

    public Integer getMd_isSmoke() {
        return md_isSmoke;
    }

    public void setMd_isSmoke(Integer md_isSmoke) {
        this.md_isSmoke = md_isSmoke;
    }

    public Integer getMd_isSunVisor() {
        return md_isSunVisor;
    }

    public void setMd_isSunVisor(Integer md_isSunVisor) {
        this.md_isSunVisor = md_isSunVisor;
    }

    public Integer getCt_isSafetyBelt() {
        return ct_isSafetyBelt;
    }

    public void setCt_isSafetyBelt(Integer ct_isSafetyBelt) {
        this.ct_isSafetyBelt = ct_isSafetyBelt;
    }

    public Integer getCt_isSunVisor() {
        return ct_isSunVisor;
    }

    public void setCt_isSunVisor(Integer ct_isSunVisor) {
        this.ct_isSunVisor = ct_isSunVisor;
    }

    public Integer getMc_isHelmet() {
        return mc_isHelmet;
    }

    public void setMc_isHelmet(Integer mc_isHelmet) {
        this.mc_isHelmet = mc_isHelmet;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

    public double getRlblt() {
        return rlblt;
    }

    public void setRlblt(double rlblt) {
        this.rlblt = rlblt;
    }

    public Integer getHphm_state() {
        return hphm_state;
    }

    public void setHphm_state(Integer hphm_state) {
        this.hphm_state = hphm_state;
    }

    public String getHphm_color() {
        return hphm_color;
    }

    public void setHphm_color(String hphm_color) {
        this.hphm_color = hphm_color;
    }

    public String getWord_Style() {
        return word_Style;
    }

    public void setWord_Style(String word_Style) {
        this.word_Style = word_Style;
    }

    public String getHphm_type() {
        return hphm_type;
    }

    public void setHphm_type(String hphm_type) {
        this.hphm_type = hphm_type;
    }

    public Integer getHphm_tps() {
        return hphm_tps;
    }

    public void setHphm_tps(Integer hphm_tps) {
        this.hphm_tps = hphm_tps;
    }

    public double getHphm_rlblt() {
        return hphm_rlblt;
    }

    public void setHphm_rlblt(double hphm_rlblt) {
        this.hphm_rlblt = hphm_rlblt;
    }

    public String getEhphm_rlblt() {
        return ehphm_rlblt;
    }

    public void setEhphm_rlblt(String ehphm_rlblt) {
        this.ehphm_rlblt = ehphm_rlblt;
    }
}
