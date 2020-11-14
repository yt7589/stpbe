package com.zhuanjingkj.zjcbe.api.controller.account;

import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.LoginRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.requestDTO.RegisterRequestDTO;
import com.zhuanjingkj.zjcbe.business.dto.account.responseDTO.LoginResponseDTO;
import com.zhuanjingkj.zjcbe.business.service.account.IAccountService;
import com.zhuanjingkj.zjcbe.business.service.auth.IAuthService;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.SimpleResultDTO;
import com.zhuanjingkj.zjcbe.utility.output.CustomOutputUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "登陆注册")
@RestController
@RequestMapping("/vehicle/api")
public class LoginController {

    @Autowired
    IAuthService authService;

    @Autowired
    IAccountService accountService;

    @ApiOperation(value = "登陆", notes = "请联系系统管理获取授权appId与secretKey")
    @PostMapping("/login/index")
    public ResultDTO<LoginResponseDTO> login(@Validated @RequestBody LoginRequestDTO requestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CustomOutputUtility.paramError(bindingResult);
        }
        return authService.login(requestDTO);
    }

    @ApiOperation(value = "注册", notes = "请联系系统管理获取授权appId与secretKey,sign签名方法：MD5(secretKey+mobileCode)大写32位")
    @PostMapping("/register")
    public SimpleResultDTO register(@Validated @RequestBody RegisterRequestDTO requestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CustomOutputUtility.paramError(bindingResult);
        }
        return accountService.register(requestDTO);
    }

}
