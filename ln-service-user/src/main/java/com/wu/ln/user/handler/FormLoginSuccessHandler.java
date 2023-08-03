package com.wu.ln.user.handler;

import cn.hutool.core.lang.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.ln.bo.R;
import com.wu.ln.util.Constant;
import com.wu.ln.util.CreateR;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class FormLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedisTemplate<String, String> redisTemplate;

    private ObjectMapper objectMapper;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    private void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        redisTemplate.delete(Constant.LOCKED_REDIS_KEY + authentication.getName());

        response.setContentType("application/json;charset=UTF-8");

        String accessToken = UUID.randomUUID().toString(true);
        R<String> result = CreateR.createSuccessResult("登录成功", accessToken);

        redisTemplate.opsForValue().set(Constant.ACCESS_TOKEN_REDIS_KEY + accessToken, authentication.getName(), Constant.ACCESS_TOKEN_EXPIRE_MINUTES, TimeUnit.MINUTES);
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
