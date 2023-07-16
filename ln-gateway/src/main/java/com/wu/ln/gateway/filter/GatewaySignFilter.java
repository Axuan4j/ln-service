package com.wu.ln.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GatewaySignFilter implements GlobalFilter, Ordered {

    private final Logger logger = LoggerFactory.getLogger(GatewaySignFilter.class);

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        logger.info("request path: {}", request.getPath());
        PathContainer pathContainer = request.getPath().pathWithinApplication();
        // 添加gatewayKey，防止下游接口直接被访问
        ServerHttpRequest.Builder mutate = request.mutate();
        mutate.header("gateway", "routed");
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
}
