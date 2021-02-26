package com.zhuanjingkj.stpbe.common.mapper;

import com.zhuanjingkj.stpbe.data.dto.SmSysInfoDTO;
import com.zhuanjingkj.stpbe.data.dto.SmUserDTO;
import com.zhuanjingkj.stpbe.data.rto.sm.AddUserToSmRTO;
import com.zhuanjingkj.stpbe.data.dto.SmRoleDTO;
import com.zhuanjingkj.stpbe.data.rto.sm.UpdateUserInfoRTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmDcMapper {

    List<SmUserDTO> getUsers(@Param("startIndex") Integer startIndex, @Param("amount") Integer amount,
                             @Param("loginName") String loginName, @Param("userName") String userName,
                             @Param("phone") String phone);

    Integer getUserCount();

    Integer delUser(@Param("userId") Integer userId);

    Integer addUser(@Param("rto") AddUserToSmRTO rto);

    Integer uptUserInfo(@Param("rto") UpdateUserInfoRTO rto);

    List<SmRoleDTO> getRoles(@Param("startIndex") Integer startIndex, @Param("amount") Integer amount);

    Integer getRoleCount();

    SmUserDTO getUserInfo(@Param("userId") long userId);

    SmSysInfoDTO getSysInfo();

    Integer uptSysInfo(@Param("qyName") String qyName, @Param("qyImgUrl") String qyImgUrl, @Param("sysName") String sysName,
                       @Param("qyIcp") String qyIcp, @Param("ownership") String ownership);
}
