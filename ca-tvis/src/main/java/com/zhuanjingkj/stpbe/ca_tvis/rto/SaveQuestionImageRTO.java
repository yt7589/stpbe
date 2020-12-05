package com.zhuanjingkj.stpbe.ca_tvis.rto;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

public class SaveQuestionImageRTO extends BaseRTO {
    @JSONField(name = "imageName")
    private String imageName;
    @JSONField(name = "brandId")
    private int brandId;
    @JSONField(name = "bmyId")
    private int bmyId;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getBmyId() {
        return bmyId;
    }

    public void setBmyId(int bmyId) {
        this.bmyId = bmyId;
    }
}
