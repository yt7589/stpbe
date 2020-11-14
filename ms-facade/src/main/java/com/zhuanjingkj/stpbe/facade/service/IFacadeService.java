package com.zhuanjingkj.stpbe.facade.service;

import com.zhuanjingkj.stpbe.data.dto.GetUserInfoDTO;
import com.zhuanjingkj.stpbe.data.dto.LoginDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.LoginRTO;

public interface IFacadeService {
    public ResultDTO<LoginDTO> login(LoginRTO rto);
    public ResultDTO<GetUserInfoDTO> getUserInfo(String platform, String version, String userIdStr);
}
