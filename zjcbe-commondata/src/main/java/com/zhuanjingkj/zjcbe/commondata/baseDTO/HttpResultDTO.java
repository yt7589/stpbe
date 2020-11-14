package com.zhuanjingkj.zjcbe.commondata.baseDTO;


import com.zhuanjingkj.zjcbe.commondata.enums.HttpResponseStatus;

import java.io.Serializable;

/**
 * @description: http 接口通用返回结果
 * @author: wangliying
 * @create: 2018-06-13
 **/
public class HttpResultDTO implements Serializable {


    private static final long serialVersionUID = 4062692170023843811L;
    /**
     * 结果返回的确定状态
     */
    private HttpResponseStatus responseStatus;

    /**
     * 状体码
     */
    private Integer statusCode;

    /**
     * 响应内容
     */
    private String content;


    public HttpResultDTO(HttpResponseStatus responseStatus, Integer statusCode, String content) {
        this.responseStatus = responseStatus;
        this.statusCode = statusCode;
        this.content = content;
    }

    public HttpResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(HttpResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    /**
     * 获取状体码
     *
     * @return 状体码
     */
    public Integer getStatusCode() {
        return statusCode;
    }


    /**
     * 设置状体码
     *
     * @param statusCode 状体码
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }


    /**
     * 获取响应内容
     *
     * @return 响应内容
     */
    public String getContent() {
        return content;
    }


    /**
     * 设置响应内容
     *
     * @param content 响应内容
     */
    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "HttpResultDTO [responseStatus" + responseStatus + " , statusCode=" + statusCode + ", content=" + content + "]";
    }

}
