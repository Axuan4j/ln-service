package com.wu.ln.message.config;

import com.wu.ln.component.GatewayFilterHandlerInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final GatewayFilterHandlerInterceptor gatewayFilterHandlerInterceptor;

    public WebMvcConfiguration(GatewayFilterHandlerInterceptor gatewayFilterHandlerInterceptor) {
        this.gatewayFilterHandlerInterceptor = gatewayFilterHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(gatewayFilterHandlerInterceptor).addPathPatterns("/**");
    }

    // 负载均衡权重
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
