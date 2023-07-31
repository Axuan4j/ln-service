package com.wu.ln.authorization.sercurity.email;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.ln.authorization.sercurity.SaveOauthParamsSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class EmailAuthenticationSuccessHandler extends SaveOauthParamsSuccessHandler {

    public EmailAuthenticationSuccessHandler(ObjectMapper objectMapper) {
        super(objectMapper);
    }
}
