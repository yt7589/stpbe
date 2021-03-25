package com.zhuanjingkj.stpbe.tmdp.interceptor;

import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
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
        requestTemplate.header(PropUtil.getValue("AUTH_USER_HEADER"), uidStr);
    }
}
