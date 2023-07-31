package com.wu.ln.authorization.sercurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.ln.util.CreateR;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;

public abstract class SaveOauthParamsSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;

    public SaveOauthParamsSuccessHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        String result;
        if (savedRequest == null) {
            result = objectMapper.writeValueAsString(CreateR.createSuccessResult("登陆成功", "/index"));
        } else {
            result = objectMapper.writeValueAsString(CreateR.createSuccessResult("登陆成功", savedRequest.getRedirectUrl()));
        }
        response.getWriter().write(result);
    }
}
