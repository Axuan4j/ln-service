package com.wu.ln.authorization.sercurity.email;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.ln.util.CreateR;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType("application/json;charset=UTF-8");
        String result = objectMapper.writeValueAsString(CreateR.createCustomResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception.getMessage(), null));
        response.getWriter().write(result);
    }
}
