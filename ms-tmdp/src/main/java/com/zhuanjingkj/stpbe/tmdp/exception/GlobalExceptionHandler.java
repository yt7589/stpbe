package com.zhuanjingkj.stpbe.tmdp.exception;

import com.zhuanjingkj.stpbe.data.dto.ResultDTO;
import com.zhuanjingkj.stpbe.data.dto.Code;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

/**
 * author by guoqiang
 * date on 2020.11.25
 **/

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ValidationException .class)
    @ResponseBody
    public ResultDTO handleValidationException(ValidationException  exception){
        logger.error("validation excption is {}",exception);
        return ResultDTO.error(Code.PARAMETER_ERROR,exception.getMessage().split(": ")[1]);
    }


    @ExceptionHandler(Exception .class)
    @ResponseBody
    public ResultDTO handleException(Exception  exception){
        logger.error("excption is {}",exception);
        return ResultDTO.error(Code.UNKNOWN_ERROR,"未知错误");
    }

    @ExceptionHandler(ServiceException .class)
    @ResponseBody
    public ResultDTO handleServiceException(ServiceException  exception){
        logger.error("service excption is {}",exception);
        return ResultDTO.error(exception.getCode(),exception.getMessage());
    }
}
