package com.zhuanjingkj.zjcbe.api.aspect;


import com.zhuanjingkj.zjcbe.business.contentext.AccountContextBO;
import com.zhuanjingkj.zjcbe.business.contentext.AuthContextHolder;
import com.zhuanjingkj.zjcbe.business.service.auth.IAuthService;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.utility.output.CustomOutputUtility;
import com.zhuanjingkj.zjcbe.utility.string.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: liuxiaogang.bj
 * @create: 2019/7/22 9:48
 **/

@Aspect
@Component
public class BaseAuthorizeAspect {

    @Autowired
    IAuthService authService;

    @Pointcut("@annotation(com.zhuanjingkj.zjcbe.api.annotation.BaseAuthorize)")
    public void doAuthorize() {
    }

    @Around("doAuthorize()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String accessToken = request.getHeader("accessToken");
        if (StringUtils.isBlank(accessToken)) {
            return CustomOutputUtility.excuteFail("header中授权token缺失");
        }
        ResultDTO<AccountContextBO> contextResultDTO = authService.getAuthContextByToken(accessToken);
        if (contextResultDTO.getSuccess() && contextResultDTO.getCode() == 1) {
            if (contextResultDTO.getData() != null) {
                AuthContextHolder.setAuthContext(contextResultDTO.getData());
            } else {
                return CustomOutputUtility.excuteFail("获取用户上下文信息失败，请重新登陆");
            }
        } else {
            return contextResultDTO;
        }
        return pjp.proceed();
    }
}
