package com.wu.ln.component;

import com.wu.ln.bo.R;
import com.wu.ln.exceptions.AuthorizedException;
import com.wu.ln.util.CreateR;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ServiceExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public R<?> handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        if (e instanceof AuthorizedException authorizedException) {
            printRequest(request, "授权认证异常:" + e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return CreateR.createCustomResult(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase() + ":" + authorizedException.getMessage(), null);
        } else if (e instanceof BindException bindException) {
            printRequest(request, "参数验证异常:" + e.getMessage());
            // 获取验证失败的信息
            BindingResult bindingResult = bindException.getBindingResult();
            FieldError fieldError = bindingResult.getFieldError();
            String errMsg;
            if (fieldError != null) {
                errMsg = fieldError.getField() + ":" + fieldError.getDefaultMessage();
            } else {
                errMsg = "参数校验失败";
            }
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return CreateR.createCustomResult(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase() + ":" + errMsg, null);
        }

        printRequest(request, "服务端未识别异常:" + e.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return CreateR.createCustomResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase() + ":" + e.getMessage(), null);
    }

    private void printRequest(HttpServletRequest request, String err) {
        logger.info("request url: {}, method: {}, exception: {}", request.getRequestURI(), request.getMethod(), err);
    }
}
