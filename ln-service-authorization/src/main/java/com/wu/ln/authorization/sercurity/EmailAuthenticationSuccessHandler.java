package com.wu.ln.authorization.sercurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;


@Component
public class EmailAuthenticationSuccessHandler extends SaveOauthParamsSuccessHandler {

    public EmailAuthenticationSuccessHandler(ObjectMapper objectMapper) {
        super(objectMapper);
    }
}
