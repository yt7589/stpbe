package com.zhuanjingkj.zjcbe.business.dto.account.requestDTO;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class PasswordModifyRequestDTO {

	@ApiModelProperty("原始密码")
	@NotBlank(message = "用户名不能为空")
	private String oldPassword;

	@ApiModelProperty("新密码")
	@NotBlank(message = "新密码不能为空")
	@Length(min = 6, message = "请至少输入6位以上密码")
	private String newPassword;

	@ApiModelProperty("密码确认")
	@NotBlank(message = "密码确认不能为空")
	@Length(min = 6, message = "请至少输入6位以上密码")
	private String newPasswordConfirm;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}

	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}
}
