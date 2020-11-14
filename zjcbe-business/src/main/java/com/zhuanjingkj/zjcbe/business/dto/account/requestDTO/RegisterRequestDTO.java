package com.zhuanjingkj.zjcbe.business.dto.account.requestDTO;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RegisterRequestDTO {

	@ApiModelProperty("用户名")
	@NotBlank(message = "用户名不能为空")
	private String userCode;

	@ApiModelProperty("公司名称")
	@NotBlank(message = "公司名称")
	private String companyName;

	@ApiModelProperty("城市")
	@NotBlank(message = "城市")
	private String city;

	@ApiModelProperty("手机号")
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = "^1\\d{10}$", message = "手机号格式有误")
	private String mobileCode;

	@ApiModelProperty("密码")
	@Pattern(regexp = "^.{6,20}$", message = "密码格式有误，请输入6~20位密码")
	private String userPassword;

	@ApiModelProperty("验证码")
	@NotBlank(message = "验证码不能为空")
	private String verifyCode;

	@NotBlank(message = "授权appId不能为空")
	@ApiModelProperty("授权appId")
	private String appId;

	@NotBlank(message = "接口签名不能为空")
	@ApiModelProperty("接口签名")
	@Length(min = 32, message = "签名无效")
	private String sign;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobileCode() {
		return mobileCode;
	}

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
