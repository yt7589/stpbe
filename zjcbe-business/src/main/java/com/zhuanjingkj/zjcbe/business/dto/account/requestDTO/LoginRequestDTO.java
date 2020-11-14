package com.zhuanjingkj.zjcbe.business.dto.account.requestDTO;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class LoginRequestDTO {

	@NotBlank(message = "用户名不能为空")
	@ApiModelProperty("用户名")
	private String userCode;

	@NotBlank(message = "用户密码不能为空")
	@ApiModelProperty("用户密码")
	private String userPassword;

	@NotBlank(message = "授权appId不能为空")
	@ApiModelProperty("授权appId")
	private String appId;

	@NotBlank(message = "密钥不能为空")
	@ApiModelProperty("密钥")
	@Length(min = 8, message = "密钥无效，密钥为8位以上字符串")
	private String secretKey;

	@ApiModelProperty("授权主机IP/域名")
	private String host;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
