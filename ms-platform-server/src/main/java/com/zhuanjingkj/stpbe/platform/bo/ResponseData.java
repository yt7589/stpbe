package com.zhuanjingkj.stpbe.platform.bo;

import com.zhuanjingkj.stpbe.platform.util.ResponseCode;
import com.zhuanjingkj.stpbe.platform.util.ResponseMessage;

import java.util.ArrayList;

public class ResponseData<T> {

    private Integer code;
    private String message;
    private T data;

    public  ResponseData(Integer code, String message, T data) {
        this(code, message);
        this.data = data;
    }
    public ResponseData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public static ResponseData success(){
        return new ResponseData(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static <T> ResponseData<T> success(T data){
        return new ResponseData(ResponseCode.SUCCESS, ResponseMessage.SUCCESS,data);
    }

    public static ResponseData error(Integer code,String message){
        return new ResponseData(code, message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
