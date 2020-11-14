package com.zhuanjingkj.zjcbe.domain.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

@TableName("vehicle_analysis_hptz")
public class VehicleAnalysisHptzEntity {

  @TableId(value = "id",type = IdType.AUTO )
  private int id;
  @TableField("analysisId")
  private String analysisId;
  @TableField("createTime")
  private Date createTime;
  @TableField("ywlshp")
  private String ywlshp;
  @TableField("hpzt")
  private String hpzt;
  @TableField("hpys")
  private String hpys;
  @TableField("hpkxd")
  private String hpkxd;
  @TableField("mwhpkxd")
  private String mwhpkxd;
  @TableField("hpgg")
  private String hpgg;
  @TableField("hpwz")
  private String hpwz;
  @TableField("hpzl")
  private String hpzl;
  @TableField("hphm")
  private String hphm;

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

  public String getYwlshp() {
    return ywlshp;
  }

  public void setYwlshp(String ywlshp) {
    this.ywlshp = ywlshp;
  }

  public String getHpzt() {
    return hpzt;
  }

  public void setHpzt(String hpzt) {
    this.hpzt = hpzt;
  }

  public String getHpys() {
    return hpys;
  }

  public void setHpys(String hpys) {
    this.hpys = hpys;
  }

  public String getHpkxd() {
    return hpkxd;
  }

  public void setHpkxd(String hpkxd) {
    this.hpkxd = hpkxd;
  }

  public String getMwhpkxd() {
    return mwhpkxd;
  }

  public void setMwhpkxd(String mwhpkxd) {
    this.mwhpkxd = mwhpkxd;
  }

  public String getHpgg() {
    return hpgg;
  }

  public void setHpgg(String hpgg) {
    this.hpgg = hpgg;
  }

  public String getHpwz() {
    return hpwz;
  }

  public void setHpwz(String hpwz) {
    this.hpwz = hpwz;
  }

  public String getHpzl() {
    return hpzl;
  }

  public void setHpzl(String hpzl) {
    this.hpzl = hpzl;
  }

  public String getHphm() {
    return hphm;
  }

  public void setHphm(String hphm) {
    this.hphm = hphm;
  }
}
