package com.wu.ln.authorization.sercurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class FormAuthenticationSuccessHandler extends SaveOauthParamsSuccessHandler {

    public FormAuthenticationSuccessHandler(ObjectMapper objectMapper) {
        super(objectMapper);
    }

}
