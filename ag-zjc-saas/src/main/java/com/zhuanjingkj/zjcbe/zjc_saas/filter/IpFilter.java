package com.zhuanjingkj.zjcbe.zjc_saas.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zhuanjingkj.zjcbe.data.dto.ResultDTO;
import com.zhuanjingkj.zjcbe.data.dto.SystemInfoDTO;
import com.zhuanjingkj.zjcbe.zjc_saas.common.ZjcSaasConst;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class IpFilter extends ZuulFilter {
    private List<String> ips;
    public IpFilter() {
        super();
        System.out.println("IpFilter 1");
        ips = new ArrayList<>();
        ips.add("192.168.2.3");
        System.out.println("IpFilter 2");
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        System.out.println("IpFilter.shouldFilter 1");
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
        String ip = getIpAddr(ctx.getRequest());
        if (ips.contains(ip)) {
            ctx.set(ZjcSaasConst.ZUUL_FILTER_IS_SUCCESS, ZjcSaasConst.ZULL_FILTER_IS_SUCCESS_FALSE);
            ctx.setSendZuulResponse(false);
            ResultDTO<SystemInfoDTO> dto = new ResultDTO<>();
            SystemInfoDTO data = new SystemInfoDTO();
            data.setSystemName("Zuul_None");
            data.setVersion("unkown v0.0.1");
            dto.setCode(10);
            dto.setMsg("");
            dto.setData(data);
            //ResponseEntity.accepted().headers(null).body(null);
            HttpHeaders hdrs = new HttpHeaders();
            hdrs.add("zjc", "forTest");
            String resp = ResponseEntity.status(403).headers(hdrs).body(JSONObject.toJSONString(dto)).toString();
            ctx.setResponseBody(JSONObject.toJSONString(resp));
            ctx.getResponse().setContentType("application/json; charset=utf-8");
            return null;
        }
        ctx.set(ZjcSaasConst.ZUUL_FILTER_IS_SUCCESS, ZjcSaasConst.ZULL_FILTER_IS_SUCCESS_TRUE);
        return null;
    }

    public String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress="";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }
}
