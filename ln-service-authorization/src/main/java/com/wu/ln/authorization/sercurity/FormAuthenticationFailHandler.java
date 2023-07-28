package com.wu.ln.authorization.sercurity;

import com.alibaba.fastjson2.JSONObject;
import com.wu.ln.bo.R;
import com.wu.ln.util.CreateR;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FormAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType("application/json;charset=UTF-8");

        R<?> result = CreateR.createCustomResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception.getMessage(), null);
        response.getWriter().write(JSONObject.toJSONString(result));
    }
}
