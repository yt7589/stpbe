package com.zhuanjingkj.zjcbe.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

@TableName("vehicle_analysis_jsxwtz")
public class VehicleAnalysisJsxwtzEntity {

  @TableId(value = "id",type = IdType.AUTO )
  private int id;
  @TableField("analysisId")
  private String analysisId;
  @TableField("createTime")
  private Date createTime;
  @TableField("zjsksj")
  private String zjsksj;
  @TableField("mtcbdtk")
  private String mtcbdtk;
  @TableField("zjsbjaqd")
  private String zjsbjaqd;
  @TableField("zjsddh")
  private String zjsddh;
  @TableField("fjszyb")
  private String fjszyb;
  @TableField("fjsbjaqd")
  private String fjsbjaqd;
  @TableField("zjscy")
  private String zjscy;
  @TableField("zjszyb")
  private String zjszyb;

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

  public String getZjsksj() {
    return zjsksj;
  }

  public void setZjsksj(String zjsksj) {
    this.zjsksj = zjsksj;
  }

  public String getMtcbdtk() {
    return mtcbdtk;
  }

  public void setMtcbdtk(String mtcbdtk) {
    this.mtcbdtk = mtcbdtk;
  }

  public String getZjsbjaqd() {
    return zjsbjaqd;
  }

  public void setZjsbjaqd(String zjsbjaqd) {
    this.zjsbjaqd = zjsbjaqd;
  }

  public String getZjsddh() {
    return zjsddh;
  }

  public void setZjsddh(String zjsddh) {
    this.zjsddh = zjsddh;
  }

  public String getFjszyb() {
    return fjszyb;
  }

  public void setFjszyb(String fjszyb) {
    this.fjszyb = fjszyb;
  }

  public String getFjsbjaqd() {
    return fjsbjaqd;
  }

  public void setFjsbjaqd(String fjsbjaqd) {
    this.fjsbjaqd = fjsbjaqd;
  }

  public String getZjscy() {
    return zjscy;
  }

  public void setZjscy(String zjscy) {
    this.zjscy = zjscy;
  }

  public String getZjszyb() {
    return zjszyb;
  }

  public void setZjszyb(String zjszyb) {
    this.zjszyb = zjszyb;
  }
}
