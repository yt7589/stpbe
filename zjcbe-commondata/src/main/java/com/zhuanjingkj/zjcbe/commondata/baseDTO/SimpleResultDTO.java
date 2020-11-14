package com.zhuanjingkj.zjcbe.commondata.baseDTO;

import java.io.Serializable;

/**
 * @description:
 * @author: wangliying
 * @create: 2018-06-15
 **/
public class SimpleResultDTO implements Serializable {
    private static final long serialVersionUID = 3580956726325083354L;
    /**
     * 是否成功
     */
    protected boolean success = false;

    /**
     * 返回信息
     */
    protected String message;

    /**
     * 错误代码
     */
    protected int code;

    /**
     * 默认构造器
     */
    public SimpleResultDTO() {

    }

    /**
     * @param success 是否成功
     * @param message 返回的消息
     */
    public SimpleResultDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * @param success 是否成功
     */
    public SimpleResultDTO(boolean success) {
        this.success = success;
        this.message = "操作成功";
    }

    /**
     * @param code
     * @param message
     */
    public SimpleResultDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public SimpleResultDTO(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
