package com.zhuanjingkj.zjcbe.utility.output;

import com.zhuanjingkj.zjcbe.commondata.baseDTO.ResultDTO;
import com.zhuanjingkj.zjcbe.commondata.baseDTO.SimpleResultDTO;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

/**
 * @description: 自定义输出
 * @author: liuxiaogang.bj
 * @create: 2019/5/7 17:47
 **/
public class CustomOutputUtility {

    /**
     * 执行成功
     *
     * @return
     */
    public static SimpleResultDTO excuteSuccess() {
        SimpleResultDTO resultDTO = new SimpleResultDTO();
        resultDTO.setSuccess(true);
        resultDTO.setMessage("success");
        resultDTO.setCode(1);
        return resultDTO;
    }

    /**
     * 执行成功
     *
     * @param responseDTO
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> excuteSuccess(T responseDTO) {
        ResultDTO<T> resultDTO = new ResultDTO();
        resultDTO.setData(responseDTO);
        resultDTO.setSuccess(true);
        resultDTO.setMessage("success");
        resultDTO.setCode(1);
        return resultDTO;
    }

    /**
     * 执行失败
     *
     * @param errorMess
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> excuteFail(String errorMess) {
        ResultDTO<T> resultDTO = new ResultDTO();
        resultDTO.setData(null);
        resultDTO.setSuccess(false);
        resultDTO.setMessage(errorMess);
        resultDTO.setCode(0);
        return resultDTO;
    }

    /**
     * 执行失败，返回错误码
     *
     * @param errorCode
     * @param errorMess
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> excuteFail(int errorCode, String errorMess) {
        ResultDTO<T> resultDTO = new ResultDTO();
        resultDTO.setData(null);
        resultDTO.setSuccess(false);
        resultDTO.setMessage(errorMess);
        resultDTO.setCode(errorCode);
        return resultDTO;
    }

    /**
     * 参数错误校验
     *
     * @param <T>
     * @return
     */
    public static <T> ResultDTO<T> paramError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Optional<FieldError> opt = bindingResult.getFieldErrors().stream().findFirst();
            if (opt.isPresent()) {
                return excuteFail(opt.get().getDefaultMessage());
            }
        }
        return null;
    }
}
