package com.zhuanjingkj.zjcbe.api.controller.account;

import com.zhuanjingkj.zjcbe.api.annotation.BaseAuthorize;
import com.zhuanjingkj.zjcbe.business.contentext.AccountContextBO;
import com.zhuanjingkj.zjcbe.business.contentext.AuthContextHolder;
import com.zhuanjingkj.zjcbe.business.dto.power.PowerTreeDTO;
import com.zhuanjingkj.zjcbe.business.dto.power.RoleDTO;
import com.zhuanjingkj.zjcbe.business.service.auth.IAuthService;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.utility.output.CustomOutputUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "系统权限")
@RestController
@RequestMapping("/vehicle/api/power")
public class PowerController {

    @Autowired
    IAuthService authService;

    @ApiOperation(value = "获取当前用户权限菜单", notes = "")
    @RequestMapping(value = "/getpowertree", method = {RequestMethod.GET})
    @BaseAuthorize
    public ResultDTO<List<PowerTreeDTO>> getAccountPowerTree(@RequestHeader String accessToken) {
        AccountContextBO accountContextBO = AuthContextHolder.getAuthContext();
        List<PowerTreeDTO> treeResponseDTOS = authService.getAccountPowerTree(accountContextBO.getAccountId());
        return CustomOutputUtility.excuteSuccess(treeResponseDTOS);
    }

    @ApiOperation(value = "获取全部权限菜单", notes = "")
    @RequestMapping(value = "/getallpowetree", method = {RequestMethod.GET})
    @BaseAuthorize
    public ResultDTO<List<PowerTreeDTO>> getAllPowerTree(@RequestHeader String accessToken) {
        List<PowerTreeDTO> treeResponseDTOS = authService.getAllPowerTree();
        return CustomOutputUtility.excuteSuccess(treeResponseDTOS);
    }

    @ApiOperation(value = "获取角色列表", notes = "")
    @RequestMapping(value = "/getrolelist", method = {RequestMethod.GET})
    @BaseAuthorize
    public ResultDTO<List<RoleDTO>> getRoleList(@RequestHeader String accessToken) {
        List<RoleDTO> roleList = authService.getRoleList();
        return CustomOutputUtility.excuteSuccess(roleList);
    }
}
