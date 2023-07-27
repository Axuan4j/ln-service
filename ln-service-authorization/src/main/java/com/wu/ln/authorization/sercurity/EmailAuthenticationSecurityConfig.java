package com.wu.ln.authorization.sercurity;

import com.wu.ln.authorization.config.EmailAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class EmailAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private EmailAuthenticationSuccessHandler emailAuthenticationSuccessHandler;

    private EmailAuthenticationFailHandler emailAuthenticationFailHandler;

    private EmailAuthenticationProvider emailAuthenticationProvider;

    @Autowired
    public void setEmailAuthenticationSuccessHandler(EmailAuthenticationSuccessHandler emailAuthenticationSuccessHandler) {
        this.emailAuthenticationSuccessHandler = emailAuthenticationSuccessHandler;
    }

    @Autowired
    public void setEmailAuthenticationFailHandler(EmailAuthenticationFailHandler emailAuthenticationFailHandler) {
        this.emailAuthenticationFailHandler = emailAuthenticationFailHandler;
    }

    @Autowired
    public void setEmailAuthenticationProvider(EmailAuthenticationProvider emailAuthenticationProvider) {
        this.emailAuthenticationProvider = emailAuthenticationProvider;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        EmailCodeAuthenticationFilter emailCodeAuthenticationFilter = new EmailCodeAuthenticationFilter();
        emailCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        emailCodeAuthenticationFilter.setAuthenticationSuccessHandler(emailAuthenticationSuccessHandler);
        emailCodeAuthenticationFilter.setAuthenticationFailureHandler(emailAuthenticationFailHandler);
        builder.authenticationProvider(emailAuthenticationProvider)
                .addFilterAfter(emailCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
