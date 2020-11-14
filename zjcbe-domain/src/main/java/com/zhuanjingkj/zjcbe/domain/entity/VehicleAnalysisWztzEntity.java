package com.zhuanjingkj.zjcbe.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

@TableName("vehicle_analysis_wztz")
public class VehicleAnalysisWztzEntity {

  @TableId(value = "id",type = IdType.AUTO )
  private int id;
  @TableField("analysisId")
  private String analysisId;
  @TableField("createTime")
  private Date createTime;
  @TableField("clwz")
  private String clwz;
  @TableField("psfx")
  private String psfx;

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

  public String getClwz() {
    return clwz;
  }

  public void setClwz(String clwz) {
    this.clwz = clwz;
  }

  public String getPsfx() {
    return psfx;
  }

  public void setPsfx(String psfx) {
    this.psfx = psfx;
  }
}
