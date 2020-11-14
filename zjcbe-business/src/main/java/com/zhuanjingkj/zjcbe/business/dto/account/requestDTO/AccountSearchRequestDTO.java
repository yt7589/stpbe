package com.zhuanjingkj.zjcbe.business.dto.account.requestDTO;

import com.zhuanjingkj.zjcbe.business.dto.PageSearchRequestDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class AccountSearchRequestDTO extends PageSearchRequestDTO {

    @ApiModelProperty("姓名")
    private String userName;

    @ApiModelProperty("手机号")
    private String mobileCode;

    @ApiModelProperty("部门Id列表")
    private List<Integer> deptIds;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileCode() {
        return mobileCode;
    }

    public void setMobileCode(String mobileCode) {
        this.mobileCode = mobileCode;
    }

    public List<Integer> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Integer> deptIds) {
        this.deptIds = deptIds;
    }
}
