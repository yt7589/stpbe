package com.zhuanjingkj.zjcbe.zjc_saas.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zhuanjingkj.zjcbe.common.AppConst;
import com.zhuanjingkj.zjcbe.data.dto.BaseDTO;
import com.zhuanjingkj.zjcbe.data.dto.ResultDTO;
import com.zhuanjingkj.zjcbe.zjc_saas.common.ZjcSaasConst;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AuthFilter  extends ZuulFilter {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    private List<String> mssWhiteList;

    public AuthFilter() {
        super();
        System.out.println("AuthFilter 1");
        mssWhiteList = new ArrayList<>();
        mssWhiteList.add("ms-facade/facade/login");
        mssWhiteList.add("ms-facade/facade/register");
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        System.out.println("AuthFilter.shouldFilter");
        RequestContext ctx = RequestContext.getCurrentContext();
        String isSucess = (String)ctx.get(ZjcSaasConst.ZUUL_FILTER_IS_SUCCESS);
        if (isSucess == null || isSucess.equals(ZjcSaasConst.ZULL_FILTER_IS_SUCCESS_TRUE)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestUri = ctx.getRequest().getRequestURI();
        boolean isInWhiteList = false;
        for (String serviceName : mssWhiteList) {
            if (requestUri.indexOf(serviceName) >= 0) {
                isInWhiteList = true;
            }
        }
        if (isInWhiteList) {
            ctx.set(ZjcSaasConst.ZUUL_FILTER_IS_SUCCESS, ZjcSaasConst.ZULL_FILTER_IS_SUCCESS_TRUE);
        } else {
            // 获取Authorization头
            String token = ctx.getRequest().getHeader("Authorization");
            long userId = 0;
            try {
                Claims cs = Jwts.parser().setSigningKey(AppConst.JWT_KEY.getBytes()).parseClaimsJws(token).getBody();
                String userIdStr = cs.get("userId", String.class);
                userId = Long.parseLong(userIdStr);
            } catch (Exception ex) {
                System.out.println("Verify JWT exception: " + ex.getMessage() + "!");
            }
            if (userId > 0) {
                ctx.set(ZjcSaasConst.ZUUL_FILTER_IS_SUCCESS, ZjcSaasConst.ZULL_FILTER_IS_SUCCESS_TRUE);
                ctx.addZuulRequestHeader(AppConst.AUTH_USER_HEADER, "" + userId);
                Object userIdStr = redisTemplate.opsForValue().get(AppConst.AUTH_REDIS_USER_PREFIX + userId);
                return null;
            }
            ctx.set(ZjcSaasConst.ZUUL_FILTER_IS_SUCCESS, ZjcSaasConst.ZULL_FILTER_IS_SUCCESS_FALSE);
            ResultDTO<BaseDTO> dto = new ResultDTO<>();
            dto.setCode(9);
            dto.setMsg("请先登录");
            //HttpHeaders hdrs = new HttpHeaders();
            //hdrs.add("zjc", "forTest");
            //String resp = ResponseEntity.status(403).headers(hdrs).
            // body(JSONObject.toJSONString(dto)).toString();
            ctx.setResponseBody(JSONObject.toJSONString(dto));
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            ctx.getResponse().addHeader("zjc-saas", "for Test v0.0.1");
            return null;
        }
        return null;
    }
}
