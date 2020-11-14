package com.zhuanjingkj.zjcbe.api.controller.account;

import com.baomidou.mybatisplus.plugins.Page;
import com.zhuanjingkj.zjcbe.api.annotation.BaseAuthorize;
import com.zhuanjingkj.zjcbe.business.contentext.AccountContextBO;
import com.zhuanjingkj.zjcbe.business.contentext.AuthContextHolder;
import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.AccountRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.AccountSearchRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.PasswordModifyRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.responseDTO.AccountResponseDTO;
import com.zhuanjingkj.zjcbe.business.service.account.IAccountService;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.SimpleResultDTO;
import com.zhuanjingkj.zjcbe.utility.output.CustomOutputUtility;
import com.zhuanjingkj.zjcbe.utility.string.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 账户管理
 * @author: liuxiaogang.bj
 * @create: 2019/9/27 17:28
 **/
@Api(description = "账户管理")
@RestController
@RequestMapping(value = "/vehicle/api/account")

public class AccountController {

    @Autowired
    IAccountService accountService;

    @ApiOperation(value = "获取登陆用户上下文信息", notes = "")
    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    @BaseAuthorize
    public ResultDTO getAuthContext(@RequestHeader String accessToken) {
        AccountContextBO contextBO = AuthContextHolder.getAuthContext();
        return CustomOutputUtility.excuteSuccess(contextBO);
    }

    @ApiOperation(value = "修改密码", notes = "")
    @PostMapping(value = "/changepassword")
    @BaseAuthorize
    public SimpleResultDTO changePassword(@RequestHeader String accessToken, @Validated @RequestBody PasswordModifyRequestDTO requestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CustomOutputUtility.paramError(bindingResult);
        }
        return accountService.changePassword(requestDTO);
    }

    @ApiOperation(value = "账户新增", notes = "新增账号，accountId参数传0或者不传")
    @PostMapping("/create")
    @BaseAuthorize
    public SimpleResultDTO create(@RequestHeader String accessToken, @Validated @RequestBody AccountRequestDTO requestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CustomOutputUtility.paramError(bindingResult);
        }
        if (StringUtils.isBlank(requestDTO.getUserPassword())) {
            return CustomOutputUtility.excuteFail("账户密码不能为空！");
        }
        return accountService.addAccount(requestDTO);
    }

    @ApiOperation(value = "账户修改", notes = "accountId必传")
    @PostMapping("/modify")
    @BaseAuthorize
    public SimpleResultDTO modify(@RequestHeader String accessToken, @Validated @RequestBody AccountRequestDTO requestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CustomOutputUtility.paramError(bindingResult);
        }
        if (requestDTO.getAccountId() <= 0) {
            return CustomOutputUtility.excuteFail("账户Id不能为空");
        }
        return accountService.modifyAccount(requestDTO);
    }

    @ApiOperation(value = "账户删除", notes = "")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST})
    @BaseAuthorize
    public SimpleResultDTO delete(@RequestHeader String accessToken, @RequestParam int accountId) {
        if (accountId <= 0) {
            return CustomOutputUtility.excuteFail("账户Id无效");
        }
        return accountService.deleteAccount(accountId);
    }

    @ApiOperation(value = "获取账户管理列表", notes = "")
    @PostMapping("/getaccountpagelist")
    @BaseAuthorize
    public ResultDTO<Page<AccountResponseDTO>> getAccountPageList(@RequestHeader String accessToken, @Validated @RequestBody AccountSearchRequestDTO requestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CustomOutputUtility.paramError(bindingResult);
        }
        Page<AccountResponseDTO> pageResult = accountService.getAccountPageList(requestDTO);
        return CustomOutputUtility.excuteSuccess(pageResult);
    }

    @ApiOperation(value = "获取账户详情信息", notes = "")
    @RequestMapping(value = "/getaccountdetail", method = {RequestMethod.GET})
    @BaseAuthorize
    public SimpleResultDTO getAccountDetail(@RequestHeader String accessToken, @RequestParam int accountId) {
        if (accountId <= 0) {
            return CustomOutputUtility.excuteFail("账户Id无效");
        }
        return accountService.getAccountDetail(accountId);
    }
}
