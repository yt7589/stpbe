package com.zhuanjingkj.stpbe.tmdp.rto;

import com.zhuanjingkj.stpbe.data.rto.BaseRTO;

import javax.validation.constraints.NotNull;

/**
 * author by guoqiang
 * date on 2020.12.10
 **/
public class DeviceRTO extends BaseRTO {

    @NotNull(message = "页码不能为空")
    private Integer pageNum ;
    @NotNull(message = "每页显示大小不能为空")
    private Integer pageSize ;
    private String cameraCode;
    private Integer cameraTypeId;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCameraCode() {
        return cameraCode;
    }

    public void setCameraCode(String cameraCode) {
        this.cameraCode = cameraCode;
    }

    public Integer getCameraTypeId() {
        return cameraTypeId;
    }

    public void setCameraTypeId(Integer cameraTypeId) {
        this.cameraTypeId = cameraTypeId;
    }
}
