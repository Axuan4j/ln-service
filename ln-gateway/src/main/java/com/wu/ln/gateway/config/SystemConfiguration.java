package com.wu.ln.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class SystemConfiguration {

    @Value("${gateway.ignorePath:}")
    private String[] ignorePath;

    @Value("${gateway.userServiceIgnorePrefix:/userProvinder/user}")
    private String userServiceIgnorePrefix;

    public String[] getIgnorePath() {
        return ignorePath;
    }

    public void setIgnorePath(String[] ignorePath) {
        this.ignorePath = ignorePath;
    }

    public String getUserServiceIgnorePrefix() {
        return userServiceIgnorePrefix;
    }

    public void setUserServiceIgnorePrefix(String userServiceIgnorePrefix) {
        this.userServiceIgnorePrefix = userServiceIgnorePrefix;
    }
}
