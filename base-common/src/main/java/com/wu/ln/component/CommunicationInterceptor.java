package com.wu.ln.component;

import com.wu.ln.util.SystemConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class CommunicationInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header(SystemConstant.GATEWAY_FILTER_KEY, SystemConstant.FILTER_INTERIOR);
    }
}