package com.zhuanjingkj.zjcbe.facade.service;

import com.zhuanjingkj.zjcbe.data.dto.GetUserInfoDTO;
import com.zhuanjingkj.zjcbe.data.dto.LoginDTO;
import com.zhuanjingkj.zjcbe.data.dto.ResultDTO;
import com.zhuanjingkj.zjcbe.data.rto.LoginRTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IFacadeService {
    public ResultDTO<LoginDTO> login(LoginRTO rto);
    public ResultDTO<GetUserInfoDTO> getUserInfo(String platform, String version, String userIdStr);
}
