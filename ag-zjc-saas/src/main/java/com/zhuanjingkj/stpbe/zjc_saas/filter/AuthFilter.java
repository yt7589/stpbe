package com.zhuanjingkj.stpbe.zjc_saas.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zhuanjingkj.stpbe.common.AppConst;
import com.zhuanjingkj.stpbe.common.util.PropUtil;
import com.zhuanjingkj.stpbe.data.dto.BaseDTO;
import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.zjc_saas.common.ZjcSaasConst;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AuthFilter  extends ZuulFilter {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    private List<String> mssWhiteList;
    private final static Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    public AuthFilter() {
        super();
        System.out.println("AuthFilter 1");
        mssWhiteList = new ArrayList<>();
        mssWhiteList.add("ms-facade/facade/login");
        mssWhiteList.add("ms-facade/facade/register");
        mssWhiteList.add("ms-tmdp/sm/getSysInfo");
        mssWhiteList.add("ms-tmdp/vm/ils/exportvs");
        mssWhiteList.add("ms-tmdp/vm/ils/export");
        mssWhiteList.add("ms-tmdp/dc/hp/exportAllData");
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
        logger.info("AuthFilter.run 1");
        RequestContext ctx = RequestContext.getCurrentContext();
        logger.info("AuthFilter.run 2");
        String requestUri = ctx.getRequest().getRequestURI();
        logger.info("AuthFilter.run 3");
        boolean isInWhiteList = false;
        for (String serviceName : mssWhiteList) {
            if (requestUri.indexOf(serviceName) >= 0) {
                isInWhiteList = true;
            }
        }
        logger.info("AuthFilter.run 4");
        if (isInWhiteList) {
            logger.info("AuthFilter.run 5");
            ctx.set(ZjcSaasConst.ZUUL_FILTER_IS_SUCCESS, ZjcSaasConst.ZULL_FILTER_IS_SUCCESS_TRUE);
            return true;
        } else {
            logger.info("AuthFilter.run 6");
            // 获取Authorization头
            String token = ctx.getRequest().getHeader("Authorization");
            long userId = 0;
            try {
                Claims cs = Jwts.parser().setSigningKey(PropUtil.getValue("JWT_KEY").getBytes()).parseClaimsJws(token).getBody();
                String userIdStr = cs.get("userId", String.class);
                userId = Long.parseLong(userIdStr);
            } catch (Exception ex) {
                System.out.println("Verify JWT exception: " + ex.getMessage() + "!");
            }
            logger.info("AuthFilter.run 7");
            if (userId > 0) {
                ctx.set(ZjcSaasConst.ZUUL_FILTER_IS_SUCCESS, ZjcSaasConst.ZULL_FILTER_IS_SUCCESS_TRUE);
                ctx.addZuulRequestHeader(PropUtil.getValue("AUTH_USER_HEADER"), "" + userId);
                Object userIdStr = redisTemplate.opsForValue().get(PropUtil.getValue("AUTH_REDIS_USER_PREFIX") + userId);
                return true;
            }
            logger.info("AuthFilter.run 8");
            ctx.set(ZjcSaasConst.ZUUL_FILTER_IS_SUCCESS, ZjcSaasConst.ZULL_FILTER_IS_SUCCESS_FALSE);
            ResultDTO<BaseDTO> dto = new ResultDTO<>();
            dto.setCode(9);
            dto.setMsg("请先登录");
            logger.info("AuthFilter.run 9");
            //HttpHeaders hdrs = new HttpHeaders();
            //hdrs.add("zjc", "forTest");
            //String resp = ResponseEntity.status(403).headers(hdrs).
            // body(JSONObject.toJSONString(dto)).toString();
            ctx.setResponseBody(JSONObject.toJSONString(dto));
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            ctx.getResponse().addHeader("zjc-saas", "for Test v0.0.1");
            logger.info("AuthFilter.run 10");
            return null;
        }
    }
}
