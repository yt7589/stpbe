package com.zhuanjingkj.zjcbe.commondata.baseDTO;

import java.io.Serializable;

/**
 * @description: 通用的结果返回类
 * @author: wangliying
 * @create: 2018-06-06
 **/
public class ResultDTO<T> extends SimpleResultDTO implements Serializable {

    private static final long serialVersionUID = -4562898309024336621L;
    /**
     * 装载数据
     */
    public T data;


    /**
     * 默认构造器
     */
    public ResultDTO() {

    }

    /**
     * @param success 是否成功
     * @param message 消息
     * @param data    数据
     */
    public ResultDTO(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * @param success 是否成功
     * @param message 消息
     * @param data    数据
     * @param code    状态码
     */
    public ResultDTO(boolean success, String message, T data, int code) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
