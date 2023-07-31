package com.wu.ln.authorization.sercurity.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmailAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private EmailAuthenticationFailHandler emailAuthenticationFailHandler;

    private EmailAuthenticationSuccessHandler emailAuthenticationSuccessHandler;

    private EmailAuthenticationProvider emailAuthenticationProvider;

    @Autowired
    public void setEmailAuthenticationFailHandler(EmailAuthenticationFailHandler emailAuthenticationFailHandler) {
        this.emailAuthenticationFailHandler = emailAuthenticationFailHandler;
    }

    @Autowired
    public void setEmailAuthenticationSuccessHandler(EmailAuthenticationSuccessHandler emailAuthenticationSuccessHandler) {
        this.emailAuthenticationSuccessHandler = emailAuthenticationSuccessHandler;
    }

    @Autowired
    public void setEmailAuthenticationProvider(EmailAuthenticationProvider emailAuthenticationProvider) {
        this.emailAuthenticationProvider = emailAuthenticationProvider;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        EmailCodeAuthenticationFilter emailCodeAuthenticationFilter = new EmailCodeAuthenticationFilter();
        emailCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        emailCodeAuthenticationFilter.setAuthenticationFailureHandler(emailAuthenticationFailHandler);
        emailCodeAuthenticationFilter.setAuthenticationSuccessHandler(emailAuthenticationSuccessHandler);
        List<SecurityContextRepository> delegates = new ArrayList<>();
        delegates.add(new HttpSessionSecurityContextRepository());
        delegates.add(new RequestAttributeSecurityContextRepository());
        emailCodeAuthenticationFilter.setSecurityContextRepository(
                new DelegatingSecurityContextRepository(delegates)
        );
        builder.authenticationProvider(emailAuthenticationProvider)
                .addFilterAfter(emailCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
