package com.wu.ln.message.config;

import com.wu.ln.bo.R;
import com.wu.ln.util.CreateR;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class ExceptionHandlerConfiguration {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerConfiguration.class);

    @ExceptionHandler(value = Exception.class)
    public R<?> RpcExecuteException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        logger.error("http url:{}, method:{}, rpc execute exception:{}", request.getRequestURL(), request.getMethod(), e.getMessage());
        return CreateR.createCustomResult(500, e.getMessage());
    }
}
