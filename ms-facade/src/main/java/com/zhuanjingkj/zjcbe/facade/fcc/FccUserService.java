package com.zhuanjingkj.zjcbe.facade.fcc;

import com.zhuanjingkj.zjcbe.data.dto.GetUserInfoDTO;
import com.zhuanjingkj.zjcbe.data.dto.LoginDTO;
import com.zhuanjingkj.zjcbe.data.dto.ResultDTO;
import com.zhuanjingkj.zjcbe.data.rto.LoginRTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="ms-user", path="/user")
public interface FccUserService {
    @PostMapping("/login")
    public ResultDTO<LoginDTO> login(@RequestBody LoginRTO rto);

    @GetMapping("/getUserInfo")
    public ResultDTO<GetUserInfoDTO> getUserInfo(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            @RequestParam(name = "userId") String userIdStr
    );
}
