package com.zhuanjingkj.stpbe.user.service;

import com.zhuanjingkj.stpbe.data.dto.LoginDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.LoginRTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserService {
    public ResultDTO<LoginDTO> login(@RequestBody LoginRTO rto);
}
