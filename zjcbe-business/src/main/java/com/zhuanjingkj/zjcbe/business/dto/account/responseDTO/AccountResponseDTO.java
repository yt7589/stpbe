package com.zhuanjingkj.zjcbe.business.dto.account.responseDTO;

import com.zhuanjingkj.zjcbe.domain.po.AccountPowerPO;
import com.zhuanjingkj.zjcbe.utility.string.StringUtils;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.stream.Collectors;

public class AccountResponseDTO {

    @ApiModelProperty("账户Id")
    private int accountId;

    @ApiModelProperty("账户名")
    private String userCode;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("手机号")
    private String mobileCode;

    @ApiModelProperty("角色Id")
    private int roleId;

    @ApiModelProperty("岗位编码")
    private int roleCode;

    @ApiModelProperty("岗位名称")
    private String roleName;

    @ApiModelProperty("权限文字描述")
    private String powerDesp;

    @ApiModelProperty("权限列表")
    private List<AccountPowerPO> powerList;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPowerDesp(String powerDesp) {
        this.powerDesp = powerDesp;
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

    public int getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(int roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPowerDesp() {
        if (this.getPowerList() != null && this.getPowerList().size() > 0) {
            List<String> lst = this.getPowerList().stream().map(p -> {
                return p.getPowerName();
            }).collect(Collectors.toList());
            return StringUtils.join(lst, ',');
        }
        return "";
    }

    public List<AccountPowerPO> getPowerList() {
        return powerList;
    }

    public void setPowerList(List<AccountPowerPO> powerList) {
        this.powerList = powerList;
    }
}
