package com.zhuanjingkj.stpbe.user.service;

import com.zhuanjingkj.stpbe.data.dto.LoginDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.LoginRTO;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    @Override
    public ResultDTO<LoginDTO> login(LoginRTO rto) {
        ResultDTO<LoginDTO> dto = new ResultDTO<>();
        dto.setCode(0);
        dto.setMsg("");
        LoginDTO data = new LoginDTO();
        data.setUserId(1001L);
        data.setUserName("用户1001");
        data.setRoleId(2001);
        data.setRoleName("普通用户");
        dto.setData(data);
        return dto;
    }
}
