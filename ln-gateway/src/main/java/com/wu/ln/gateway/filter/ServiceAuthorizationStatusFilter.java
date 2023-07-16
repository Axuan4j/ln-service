package com.wu.ln.gateway.filter;

import com.wu.ln.gateway.config.SystemConfiguration;
import com.wu.ln.gateway.util.AppObjectMapper;
import com.wu.ln.gateway.util.CreateR;
import com.wu.ln.gateway.util.JwtUtil;
import com.wu.ln.gateway.util.R;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceAuthorizationStatusFilter implements GlobalFilter, Ordered {

    private final SystemConfiguration systemConfiguration;

    private final AppObjectMapper appObjectMapper = new AppObjectMapper();

    public ServiceAuthorizationStatusFilter(SystemConfiguration systemConfiguration) {
        this.systemConfiguration = systemConfiguration;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String[] ignorePath = systemConfiguration.getIgnorePath();
        String requestPath = exchange.getRequest().getPath().value();
        Optional<String> filterResult = Arrays.stream(ignorePath).filter(path -> path.equals(requestPath)).findFirst();
        if (filterResult.isPresent()) {
            return chain.filter(exchange);
        } else {
            return authorizationVerify(exchange, chain);
        }
    }

    private Mono<Void> authorizationVerify(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        List<String> authorizationValues = request.getHeaders().getOrDefault("authorization", Collections.emptyList());
        if (authorizationValues.isEmpty()) {
            return authorizationFailed(response);
        } else {
            String authorization = authorizationValues.get(0);
            if (JwtUtil.getInstance().verifyToken(authorization)) {
                return chain.filter(exchange);
            } else {
                return authorizationFailed(response);
            }
        }
    }

    private Mono<Void> authorizationFailed(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        HttpHeaders headers = response.getHeaders();
        R<String> unauthorized = CreateR.createCustomResult(401, "Unauthorized", null);
        String result = appObjectMapper.writeValueAsString(unauthorized);
        headers.add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(response.bufferFactory().wrap(result.getBytes())));
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
