package com.zhuanjingkj.stpbe.data.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class ResultDTO<T extends BaseDTO> {
    @JSONField(name="code")
    protected int code;
    @JSONField(name="msg")
    protected String msg;
    @JSONField(name="data")
    protected T data;

    public ResultDTO() {
        code = Code.SUCCESS;
        msg = "";
    }

    public ResultDTO(T data){
        this.code = Code.SUCCESS;
        this.msg = "success";
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BaseDTO getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T extends BaseDTO> ResultDTO<T> success(T data){
        return new ResultDTO<>(data);
    }

    public static ResultDTO error(int code,String msg){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMsg(msg);
        return resultDTO;
    }
}
