package com.zhuanjingkj.zjcbe.user.service;

import com.zhuanjingkj.zjcbe.data.dto.GetUserInfoDTO;
import com.zhuanjingkj.zjcbe.data.dto.LoginDTO;
import com.zhuanjingkj.zjcbe.data.dto.ResultDTO;
import com.zhuanjingkj.zjcbe.data.rto.LoginRTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface IUserService {
    public ResultDTO<LoginDTO> login(@RequestBody LoginRTO rto);
}
