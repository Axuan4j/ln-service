package com.wu.ln.user.handler;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.ln.bo.R;
import com.wu.ln.util.Constant;
import com.wu.ln.util.CreateR;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class FormLoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

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
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Optional.ofNullable(request.getUserPrincipal()).ifPresent(e -> {
            if (StrUtil.isNotBlank(e.getName())) {
                String key = Constant.LOCKED_REDIS_KEY + e.getName();
                Long val = redisTemplate.opsForValue().increment(key);
                if (val != null && val == 1L) {
                    redisTemplate.expire(key, Constant.ACCOUNT_LOCKED_MINUTES, TimeUnit.MINUTES);
                }
            }
        });

        response.setContentType("application/json;charset=UTF-8");
        R<Object> result = CreateR.createCustomResult(505, "登录失败:" + exception.getMessage(), null);
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
