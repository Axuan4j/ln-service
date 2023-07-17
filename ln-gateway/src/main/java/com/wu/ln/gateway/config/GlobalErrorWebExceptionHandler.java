package com.wu.ln.gateway.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wu.ln.gateway.util.AppObjectMapper;
import com.wu.ln.gateway.util.CreateR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(-1)
@Configuration
public class GlobalErrorWebExceptionHandler implements ErrorWebExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalErrorWebExceptionHandler.class);

    private final AppObjectMapper objectMapper = new AppObjectMapper();

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        // 设置返回值类型为json
        response.getHeaders().setContentType(MediaType.valueOf("application/json;charset=UTF-8"));

        HttpStatusCode code;
        //设置返回编码
        if (ex instanceof ResponseStatusException) {
            code = ((ResponseStatusException) ex).getStatusCode();
        } else {
            code = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.setStatusCode(code);

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            try {
                //writeValueAsBytes 组装错误响应结果
                return bufferFactory.wrap(objectMapper.writeValueAsBytes(CreateR.createCustomResult(code.value(), ex.getMessage(), null)));
            } catch (JsonProcessingException e) {
                logger.error("Error writing response", ex);
                return bufferFactory.wrap(new byte[0]);
            }
        }));
    }
}