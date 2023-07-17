package com.wu.ln.component;

import com.alibaba.fastjson2.JSONObject;
import com.wu.ln.bo.R;
import com.wu.ln.util.CreateR;
import com.wu.ln.util.SystemConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class GatewayFilterHandlerInterceptor implements HandlerInterceptor {

    public GatewayFilterHandlerInterceptor() {
        logger.info("GatewayFilter init");
    }

    private final Logger logger = LoggerFactory.getLogger(GatewayFilterHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String gatewayFilter = request.getHeader(SystemConstant.GATEWAY_FILTER_KEY);
        if (!StringUtils.hasText(gatewayFilter)) {
            R<String> forbidden = CreateR.createCustomResult(403, "forbidden");
            writeFileResult(response, JSONObject.toJSONString(forbidden));
            return false;
        }
        if (!SystemConstant.FILTER_GATEWAY.equals(gatewayFilter) && !SystemConstant.FILTER_INTERIOR.equals(gatewayFilter)) {
            R<String> forbidden = CreateR.createCustomResult(403, "forbidden");
            writeFileResult(response, JSONObject.toJSONString(forbidden));
            return false;
        }

        return true;
    }


    private void writeFileResult(HttpServletResponse servletResponse, String result) {
        try {
            servletResponse.setCharacterEncoding("UTF-8");
            servletResponse.setContentType("application/json;charset=UTF-8");
            servletResponse.setStatus(403);
            servletResponse.getWriter().write(result);
        } catch (Exception e) {
            logger.error("write file result exception: {}", e.getMessage());
        }
    }
}
