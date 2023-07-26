package com.wu.ln.authorization.sercurity;

import com.wu.ln.authorization.config.EmailAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class EmailAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final EmailAuthenticationProvider emailAuthenticationProvider;

    public EmailAuthenticationSecurityConfig(EmailAuthenticationProvider emailAuthenticationProvider) {
        this.emailAuthenticationProvider = emailAuthenticationProvider;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        super.configure(builder);
        builder.authenticationProvider(emailAuthenticationProvider);
    }
}
