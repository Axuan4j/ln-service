package com.wu.ln.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemConfiguration {

    @Value("${gateway.ignorePath:}")
    private String[] ignorePath;

    public String[] getIgnorePath() {
        return ignorePath;
    }

    public void setIgnorePath(String[] ignorePath) {
        this.ignorePath = ignorePath;
    }
}
