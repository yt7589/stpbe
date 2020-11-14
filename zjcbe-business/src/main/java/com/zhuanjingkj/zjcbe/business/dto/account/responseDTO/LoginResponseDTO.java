package com.zhuanjingkj.zjcbe.business.dto.account.responseDTO;

import io.swagger.annotations.ApiModelProperty;

public class LoginResponseDTO {
    @ApiModelProperty("授权token")
    private String accessToken;

    @ApiModelProperty("用户信息")
    private AccountResponseDTO accountInfo;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccountResponseDTO getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountResponseDTO accountInfo) {
        this.accountInfo = accountInfo;
    }
}
