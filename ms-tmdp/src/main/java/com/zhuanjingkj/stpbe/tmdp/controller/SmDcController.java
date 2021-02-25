package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.data.rto.sm.AddUserToSmRTO;
import com.zhuanjingkj.stpbe.data.rto.sm.DeleteUserFromSmRTO;
import com.zhuanjingkj.stpbe.data.rto.sm.UpdateUserInfoRTO;
import com.zhuanjingkj.stpbe.tmdp.service.impl.SmDcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * System data manager 系统管理
 */
@RestController
@RequestMapping(value ="/sm")
@CrossOrigin(origins = "*")
public class SmDcController {

    @Autowired
    private SmDcService smDcService;

    /**
     * 获取用户列表
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value ="/getUsers")
    public ResultDTO<DbQrsDTO> getUsers(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestParam(name = "startIndex", required = false) Integer startIndex,
        @RequestParam(name = "amount", required = false) Integer amount,
        @RequestParam(name = "direction", required = false) Integer direction,
        @RequestParam(name = "loginName", required = false) String loginName,
        @RequestParam(name = "userName", required = false) String userName,
        @RequestParam(name = "phone", required = false) String phone
    ) {
        return getUsers_exp(startIndex, amount, direction, loginName, userName, phone);
    }

    /**
     * 删除用户
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @DeleteMapping(value ="/delUser")
    public ResultDTO<DbDeleteResultDTO> delUser(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestBody DeleteUserFromSmRTO rto
    ) {
        return delUser_exp(rto);
    }

    /**
     * 添加用户
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PostMapping(value ="/addUser")
    public ResultDTO<DbInsertResultDTO> addUser(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestBody AddUserToSmRTO rto
    ) {
        return addUser_exp(rto);
    }

    /**
     * 用户信息修改
     * @param platform
     * @param version
     * @param rto
     * @return
     */
    @PutMapping(value ="/uptUserInfo")
    public ResultDTO<DbDeleteResultDTO> uptUserInfo(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestBody UpdateUserInfoRTO rto
    ) {
        return uptUserInfo_exp(rto);
    }

    /**
     * 获取权限列表
     * @return
     */
    @GetMapping(value ="/getRoles")
    public ResultDTO<DbQrsDTO> getRoles(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestParam(name = "startIndex", required = false) Integer startIndex,
        @RequestParam(name = "amount", required = false) Integer amount,
        @RequestParam(name = "direction", required = false) Integer direction
    ) {
        return getRoles_exp(startIndex, amount, direction);
    }

    /**
     * 获取用户信息
     * @param platform
     * @param version
     * @param userId
     * @return
     */
    @GetMapping(value ="/getUserInfo")
    public ResultDTO<SmUserDTO> getUserInfo(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestParam(name = "userId", required = false) long userId
    ) {
        return getUserInfo_exp(userId);
    }

    /**
     * 获取系统配置信息
     * @param platform
     * @param version
     * @return
     */
    @GetMapping(value = "/getSysInfo")
    public ResultDTO<SmSysInfoDTO> getSysInfo(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version
    ) {
        return getSysInfo_exp();
    }
    /**
     * 系统信息配置
     * @param platform
     * @param version
     * @return
     */
    @PostMapping(value ="/uptSysInfo")
    public ResultDTO<DbInsertResultDTO> uptSysInfo(
        @RequestParam(name = "p", required = false) String platform,
        @RequestParam(name = "v", required = false) String version,
        @RequestParam(name = "file", required = false) MultipartFile file,
        @RequestParam(name = "qyName", required = false) String qyName,
        @RequestParam(name = "sysName", required = false) String sysName,
        @RequestParam(name = "qyIcp", required = false) String qyIcp,
        @RequestParam(name = "ownership", required = false) String ownership,
        HttpServletRequest request
    ) {
        return uptSysInfo_exp(file, qyName, sysName, qyIcp, ownership, request);
    }

    private ResultDTO<SmSysInfoDTO> getSysInfo_exp() {
        return smDcService.getSysInfo_exp();
    }

    private ResultDTO<DbQrsDTO> getUsers_exp(Integer startIndex, Integer amount, Integer direction,
                                             String loginName, String userName, String phone) {
        return smDcService.getUsers_exp(startIndex, amount, direction, loginName, userName, phone);
    }

    private ResultDTO<DbDeleteResultDTO> delUser_exp(DeleteUserFromSmRTO rto) {
        return smDcService.delUser_exp(rto);
    }

    private ResultDTO<DbInsertResultDTO> addUser_exp(AddUserToSmRTO rto) {
        return smDcService.addUser_exp(rto);
    }

    private ResultDTO<DbDeleteResultDTO> uptUserInfo_exp(UpdateUserInfoRTO rto) {
        return smDcService.uptUserInfo(rto);
    }

    private ResultDTO<DbQrsDTO> getRoles_exp(Integer startIndex, Integer amount, Integer direction) {
        return smDcService.getRoles_exp(startIndex, amount, direction);
    }

    private ResultDTO<SmUserDTO> getUserInfo_exp(long userId) {
        return smDcService.getUserInfo_exp(userId);
    }

    private ResultDTO<DbInsertResultDTO> uptSysInfo_exp(MultipartFile file, String qyName, String sysName,
                                                        String qyIcp, String ownership, HttpServletRequest request) {
        return smDcService.uptSysInfo_exp(file, qyName, sysName, qyIcp, ownership, request);
    }
    /**
     * 手动数据清空
     */
    @GetMapping(value = "/dc")
    public void dataClean() {
        smDcService.trkc(); //本日数据清空
        smDcService.mrkc(); //本月数据清空
    }
}