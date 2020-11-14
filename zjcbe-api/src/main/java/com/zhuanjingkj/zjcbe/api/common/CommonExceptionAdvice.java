package com.zhuanjingkj.zjcbe.api.common;

import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.utility.output.CustomOutputUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CommonExceptionAdvice {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultDTO errorHandler(
            HttpServletRequest request, HttpServletResponse response, Exception ex) {
        return CustomOutputUtility.excuteFail(500, ex.getMessage());
    }
}
