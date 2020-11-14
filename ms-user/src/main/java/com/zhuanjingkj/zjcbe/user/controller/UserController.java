package com.zhuanjingkj.zjcbe.user.controller;

import com.zhuanjingkj.zjcbe.data.dto.GetUserInfoDTO;
import com.zhuanjingkj.zjcbe.data.dto.LoginDTO;
import com.zhuanjingkj.zjcbe.data.dto.ResultDTO;
import com.zhuanjingkj.zjcbe.data.rto.LoginRTO;
import com.zhuanjingkj.zjcbe.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResultDTO<LoginDTO> login(@RequestBody LoginRTO rto) {
        return userService.login(rto);
    }

    @GetMapping("/getUserInfo")
    public ResultDTO<GetUserInfoDTO> getUserInfo(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            @RequestParam(name = "userId") String userIdStr
            ) {
        long userId = 0;
        try { userId = Long.parseLong(userIdStr); } catch (Exception ex) {}
        if (userId <=0) {
            ResultDTO<GetUserInfoDTO> dto = new ResultDTO<>();
            dto.setCode(1);
            dto.setMsg("用户不存在");
            return dto;
        }
        ResultDTO<GetUserInfoDTO> dto = new ResultDTO<>();
        dto.setCode(0);
        dto.setMsg("");
        return dto;
    }
}
