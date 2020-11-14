package com.zhuanjingkj.zjcbe.business.dto.account.requestDTO;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class AccountRequestDTO {

	@ApiModelProperty("用户Id，新增不传，修改必传")
	private int accountId;

	@ApiModelProperty("用户名")
	@NotBlank(message = "用户名不能为空")
	private String userCode;

	@ApiModelProperty("公司名称")
	@NotBlank(message = "公司名称")
	private String companyName;

	@ApiModelProperty("城市")
	@NotBlank(message = "城市")
	private String city;

	@ApiModelProperty("密码")
	@Pattern(regexp = "^1\\d{8,20}$", message = "手机号格式有误")
	private String userPassword;

	@ApiModelProperty("手机号")
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = "^1\\d{10}$", message = "手机号格式有误")
	private String mobileCode;

	@ApiModelProperty("岗位类型Id")
	@Min(value = 1, message = "请输入有效的岗位类型Id")
	private int roleId;

	@ApiModelProperty("权限Id列表")
	@Size(min = 1, message = "请选择用户权限")
	private List<Integer> powerIds;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

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

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getMobileCode() {
		return mobileCode;
	}

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public List<Integer> getPowerIds() {
		return powerIds;
	}

	public void setPowerIds(List<Integer> powerIds) {
		this.powerIds = powerIds;
	}
}
