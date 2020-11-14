package com.zhuanjingkj.zjcbe.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

@TableName("vehicle_analysis_gxhtz")
public class VehicleAnalysisGxhtzEntity {

  @TableId(value = "id",type = IdType.AUTO )
  private int id;
  @TableField("analysisId")
  private String analysisId;
  @TableField("createTime")
  private Date createTime;
  @TableField("ccztw")
  private String ccztw;
  @TableField("bj")
  private String bj;
  @TableField("gj")
  private String gj;
  @TableField("tc")
  private String tc;
  @TableField("xlj")
  private String xlj;
  @TableField("dcjqs")
  private String dcjqs;
  @TableField("cszt")
  private String cszt;
  @TableField("csps")
  private String csps;
  @TableField("csgh")
  private String csgh;
  @TableField("csch")
  private String csch;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getAnalysisId() {
    return analysisId;
  }

  public void setAnalysisId(String analysisId) {
    this.analysisId = analysisId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getCcztw() {
    return ccztw;
  }

  public void setCcztw(String ccztw) {
    this.ccztw = ccztw;
  }

  public String getBj() {
    return bj;
  }

  public void setBj(String bj) {
    this.bj = bj;
  }

  public String getGj() {
    return gj;
  }

  public void setGj(String gj) {
    this.gj = gj;
  }

  public String getTc() {
    return tc;
  }

  public void setTc(String tc) {
    this.tc = tc;
  }

  public String getXlj() {
    return xlj;
  }

  public void setXlj(String xlj) {
    this.xlj = xlj;
  }

  public String getDcjqs() {
    return dcjqs;
  }

  public void setDcjqs(String dcjqs) {
    this.dcjqs = dcjqs;
  }

  public String getCszt() {
    return cszt;
  }

  public void setCszt(String cszt) {
    this.cszt = cszt;
  }

  public String getCsps() {
    return csps;
  }

  public void setCsps(String csps) {
    this.csps = csps;
  }

  public String getCsgh() {
    return csgh;
  }

  public void setCsgh(String csgh) {
    this.csgh = csgh;
  }

  public String getCsch() {
    return csch;
  }

  public void setCsch(String csch) {
    this.csch = csch;
  }
}
