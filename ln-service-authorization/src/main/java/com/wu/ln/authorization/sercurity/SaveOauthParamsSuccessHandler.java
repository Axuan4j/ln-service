package com.wu.ln.authorization.sercurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wu.ln.util.CreateR;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        if (savedRequest == null) {
            String result = objectMapper.writeValueAsString(CreateR.createSuccessResult("登陆成功", null));
            response.getWriter().write(result);
            return;
        }
        Map<String, String[]> parameterMap = savedRequest.getParameterMap();
        Map<String, String> resultMap = new HashMap<>();

        oauth2ParamsCoverResult(parameterMap, resultMap);
        String result;
        if (resultMap.isEmpty()) {
            result = objectMapper.writeValueAsString(CreateR.createSuccessResult("登陆成功", null));
        } else {
            result = objectMapper.writeValueAsString(CreateR.createSuccessResult("登陆成功", resultMap));
        }
        response.getWriter().write(result);
    }

    public void oauth2ParamsCoverResult(Map<String, String[]> parameterMap, Map<String, String> resultMap) throws IOException, ServletException {
        if (!parameterMap.isEmpty()) {
            String[] clientId = parameterMap.getOrDefault(OAuth2ParameterNames.CLIENT_ID, new String[0]);
            setResultMap(clientId, resultMap, OAuth2ParameterNames.CLIENT_ID);

            String[] redirectUri = parameterMap.getOrDefault(OAuth2ParameterNames.REDIRECT_URI, new String[0]);
            setResultMap(redirectUri, resultMap, OAuth2ParameterNames.REDIRECT_URI);

            String[] state = parameterMap.getOrDefault(OAuth2ParameterNames.STATE, new String[0]);
            setResultMap(state, resultMap, OAuth2ParameterNames.STATE);

            String[] responseType = parameterMap.getOrDefault(OAuth2ParameterNames.RESPONSE_TYPE, new String[0]);
            setResultMap(responseType, resultMap, OAuth2ParameterNames.RESPONSE_TYPE);

            String[] scope = parameterMap.getOrDefault(OAuth2ParameterNames.SCOPE, new String[0]);
            setResultMap(scope, resultMap, OAuth2ParameterNames.SCOPE);
        }
    }

    private void setResultMap(String[] arr, Map<String, String> resultMap, String key) {
        if (arr.length > 0) {
            resultMap.put(key, arr[0]);
        }
    }
}
