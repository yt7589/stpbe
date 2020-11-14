package com.zhuanjingkj.zjcbe.facade.interceptor;

import com.zhuanjingkj.zjcbe.common.AppConst;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;

public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public FeignBasicAuthRequestInterceptor() {
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
        String uidStr = redisTemplate.opsForValue().get(sessionId);
        System.out.println("用户编号值：uidStr=" + uidStr + "! sessionId=" + sessionId + "!");
        requestTemplate.header(AppConst.AUTH_USER_HEADER, uidStr);
    }
}
