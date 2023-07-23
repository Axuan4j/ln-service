package com.wu.ln.user.service.impl;

import com.wu.ln.bo.R;
import com.wu.ln.user.entity.UserVO;
import com.wu.ln.user.service.UserService;
import com.wu.ln.util.CreateR;
import com.wu.ln.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Service
@RefreshScope
public class UserServiceImpl implements UserService {

    @Value("${server.port}")
    private Integer serverPort;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public R<String> sayHello(String name) {
        logger.info("sayHello: {}", name);
        return CreateR.createSuccessResult("request from port: " + serverPort + ", hello " + name + "!");
    }

    @Override
    public R<String> login(UserVO userVO) {
        String token = JwtUtil.getInstance().createToken(userVO.getUsername());
        return CreateR.createSuccessResult(token);
    }
}
