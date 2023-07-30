package com.wu.ln.authorization.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.ln.component.GatewayFilterHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private GatewayFilterHandlerInterceptor gatewayFilterHandlerInterceptor;

    @Autowired(required = false)
    public void setGatewayFilterHandlerInterceptor(GatewayFilterHandlerInterceptor gatewayFilterHandlerInterceptor) {
        this.gatewayFilterHandlerInterceptor = gatewayFilterHandlerInterceptor;
    }

    @Override
    public void addInterceptors(@NonNull InterceptorRegistry registry) {
        if (gatewayFilterHandlerInterceptor != null) {
            registry.addInterceptor(gatewayFilterHandlerInterceptor).addPathPatterns("/**");
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return objectMapper;
    }
}
