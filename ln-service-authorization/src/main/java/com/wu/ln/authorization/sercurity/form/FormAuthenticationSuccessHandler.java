package com.wu.ln.authorization.sercurity.form;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.ln.authorization.sercurity.SaveOauthParamsSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class FormAuthenticationSuccessHandler extends SaveOauthParamsSuccessHandler {

    public FormAuthenticationSuccessHandler(ObjectMapper objectMapper) {
        super(objectMapper);
    }

}
