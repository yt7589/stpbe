package com.zhuanjingkj.stpbe.tmdp.dto.ks;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;

public class KsSvsKsvrpDTO extends BaseDTO {
    @JSONField(name = "imgId")
    private long imgId;
    @JSONField(name = "imgUrl")
    private String imgUrl;

    public KsSvsKsvrpDTO(long imgId, String imgUrl) {
        this.imgId = imgId;
        this.imgUrl = imgUrl;
    }

    public long getImgId() {
        return imgId;
    }

    public void setImgId(long imgId) {
        this.imgId = imgId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
