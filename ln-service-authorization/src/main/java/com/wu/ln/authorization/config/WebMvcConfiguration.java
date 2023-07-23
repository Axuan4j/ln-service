package com.wu.ln.authorization.config;

import com.wu.ln.component.GatewayFilterHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private GatewayFilterHandlerInterceptor gatewayFilterHandlerInterceptor;

    @Autowired(required = false)
    public void setGatewayFilterHandlerInterceptor(GatewayFilterHandlerInterceptor gatewayFilterHandlerInterceptor) {
        this.gatewayFilterHandlerInterceptor = gatewayFilterHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (gatewayFilterHandlerInterceptor != null) {
            registry.addInterceptor(gatewayFilterHandlerInterceptor).addPathPatterns("/**");
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
