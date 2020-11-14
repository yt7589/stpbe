package com.zhuanjingkj.zjcbe.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

@TableName("vehicle_analysis_cxtz")
public class VehicleAnalysisCxtzEntity {

  @TableId(value = "id",type = IdType.AUTO )
  private int id;
  @TableField("analysisId")
  private String analysisId;
  @TableField("createTime")
  private Date createTime;
  @TableField("ppcx")
  private String ppcx;
  @TableField("cllxfl")
  private String cllxfl;
  @TableField("cllxzfl")
  private String cllxzfl;
  @TableField("ppxhms")
  private String ppxhms;
  @TableField("csys")
  private String csys;
  @TableField("clpp")
  private String clpp;
  @TableField("ppxhkxd")
  private String ppxhkxd;
  @TableField("cxnk")
  private String cxnk;

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

  public String getPpcx() {
    return ppcx;
  }

  public void setPpcx(String ppcx) {
    this.ppcx = ppcx;
  }

  public String getCllxfl() {
    return cllxfl;
  }

  public void setCllxfl(String cllxfl) {
    this.cllxfl = cllxfl;
  }

  public String getCllxzfl() {
    return cllxzfl;
  }

  public void setCllxzfl(String cllxzfl) {
    this.cllxzfl = cllxzfl;
  }

  public String getPpxhms() {
    return ppxhms;
  }

  public void setPpxhms(String ppxhms) {
    this.ppxhms = ppxhms;
  }

  public String getCsys() {
    return csys;
  }

  public void setCsys(String csys) {
    this.csys = csys;
  }

  public String getClpp() {
    return clpp;
  }

  public void setClpp(String clpp) {
    this.clpp = clpp;
  }

  public String getPpxhkxd() {
    return ppxhkxd;
  }

  public void setPpxhkxd(String ppxhkxd) {
    this.ppxhkxd = ppxhkxd;
  }

  public String getCxnk() {
    return cxnk;
  }

  public void setCxnk(String cxnk) {
    this.cxnk = cxnk;
  }
}
