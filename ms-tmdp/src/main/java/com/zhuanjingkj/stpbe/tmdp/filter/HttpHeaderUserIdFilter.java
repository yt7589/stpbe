package com.zhuanjingkj.stpbe.tmdp.filter;


import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpHeaderUserIdFilter implements Filter {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest)servletRequest;
//        HttpServletResponse response = (HttpServletResponse)servletResponse;
//        //request.setCharacterEncoding("UTF-8");
//        //response.setContentType("application/json; charset=utf-8");
//        String uidStr = request.getHeader(PropUtil.getValue("AUTH_USER_HEADER"));
//        String sessionId = request.getSession().getId();
//        redisTemplate.opsForValue().set(sessionId, uidStr + "");
//        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
