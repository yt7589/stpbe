package com.zhuanjingkj.stpbe.tmdp.controller;

import com.zhuanjingkj.stpbe.data.dto.GetUserInfoDTO;
import com.zhuanjingkj.stpbe.data.dto.LoginDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.rto.LoginRTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkMainDTO;
import com.zhuanjingkj.stpbe.tmdp.dto.DkTitfDTO;
import com.zhuanjingkj.stpbe.tmdp.service.DkTitfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/tmdp")
public class TmdpController {
    @Autowired
    private DkTitfService dkTitfService;

    @GetMapping("/dk/getDkMain")
    public ResultDTO<DkMainDTO> getDkMain(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            ServletRequest servletRequest,
            ServletResponse servletResponse
            ) {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String userIdStr = request.getHeader("suid");
        ResultDTO<DkMainDTO> dto = new ResultDTO<>();
        DkMainDTO data = new DkMainDTO();
        DkTitfDTO dkTitf = dkTitfService.countTrafficFlow();
        data.setDkTitf(dkTitf);
        dto.setData(data);
        return dto;
    }

    @GetMapping("/dk/getDkTitf")
    public ResultDTO<DkTitfDTO> getDkTitf(
            @RequestParam(name = "p") String platform,
            @RequestParam(name = "v") String version,
            ServletRequest servletRequest,
            ServletResponse servletResponse
    ) {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String userIdStr = request.getHeader("suid");
        ResultDTO<DkTitfDTO> dto = new ResultDTO<>();
        DkTitfDTO dkTitf = dkTitfService.countTrafficFlow();
        dto.setData(dkTitf);
        return dto;
    }
}
