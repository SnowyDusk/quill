package com.quill.common.core.handler;


import com.quill.common.core.constant.ResponseStatus;
import com.quill.common.core.exception.QuillException;
import com.quill.common.core.response.QuillResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 参数校验异常
     */
    @ExceptionHandler(BindException.class)
    public QuillResponse<List<String>> bindExceptionHandler(BindException e) {
        List<String> msg = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        log.error("处理参数校验异常, 异常数据为: {}", msg);
        return QuillResponse.error(ResponseStatus.REQUEST_PARAM_ERROR, msg);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(QuillException.class)
    public QuillResponse<Void> businessExceptionHandler(QuillException e) {
        log.error("处理异常类型: {}, 异常数据为: {}", e.getStatus(), e.getMessage());
        return QuillResponse.error(e.getStatus());
    }

    /**
     * 处理未知错误
     */
    @ExceptionHandler(Exception.class)
    public QuillResponse<Void> exceptionHandler(Exception e) {
        log.error("处理未知异常, 异常数据为: {}", e.getMessage());
        e.printStackTrace();
        return QuillResponse.error();
    }
}
