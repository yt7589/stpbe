package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.data.rto.sm.AddUserToSmRTO;
import com.zhuanjingkj.stpbe.data.rto.sm.DeleteUserFromSmRTO;
import com.zhuanjingkj.stpbe.data.rto.sm.UpdateUserInfoRTO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ISmDcService {

    void trkc();

    void mrkc();

    void report();

    ResultDTO<DbQrsDTO> getUsers_exp(Integer startIndex, Integer amount, Integer direction,
                                     String loginName, String userName, String phone);

    ResultDTO<DbDeleteResultDTO> delUser_exp(DeleteUserFromSmRTO rto);

    ResultDTO<DbInsertResultDTO> addUser_exp(AddUserToSmRTO rto);

    ResultDTO<DbDeleteResultDTO> uptUserInfo(UpdateUserInfoRTO rto);

    ResultDTO<DbQrsDTO> getRoles_exp(Integer startIndex, Integer amount, Integer direction);

    ResultDTO<SmUserDTO> getUserInfo_exp(long loginName);

    ResultDTO<DbInsertResultDTO> uptSysInfo_exp(String qyImgUrl, String qyName, String sysName,
                                                String qyIcp, String ownership, String city);

    ResultDTO<SmSysInfoDTO> getSysInfo_exp();

    ResultDTO<String> uploadImg_exp(MultipartFile file);

}
