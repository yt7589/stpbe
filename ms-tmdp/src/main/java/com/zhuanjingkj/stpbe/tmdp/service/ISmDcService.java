package com.zhuanjingkj.stpbe.tmdp.service;

import com.zhuanjingkj.stpbe.data.dto.*;
import com.zhuanjingkj.stpbe.data.rto.sm.AddUserToSmRTO;
import com.zhuanjingkj.stpbe.data.rto.sm.DeleteUserFromSmRTO;
import com.zhuanjingkj.stpbe.data.rto.sm.UpdateUserInfoRTO;

public interface ISmDcService {

    void trkc();

    void mrkc();

    ResultDTO<DbQrsDTO> getUsers_exp(Integer startIndex, Integer amount, Integer direction,
                                     String loginName, String userName, String phone);

    ResultDTO<DbDeleteResultDTO> delUser_exp(DeleteUserFromSmRTO rto);

    ResultDTO<DbInsertResultDTO> addUser_exp(AddUserToSmRTO rto);

    ResultDTO<DbDeleteResultDTO> uptUserInfo(UpdateUserInfoRTO rto);

    ResultDTO<DbQrsDTO> getRoles_exp(Integer startIndex, Integer amount, Integer direction);

    ResultDTO<SmUserDTO> getUserInfo_exp(long loginName);
}
