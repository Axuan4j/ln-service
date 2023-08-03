package com.wu.ln.user.config;

import com.wu.ln.user.handler.FormLoginFailHandler;
import com.wu.ln.user.handler.FormLoginSuccessHandler;
import com.wu.ln.user.provinder.FormAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityAuthorizedConfiguration {

    private FormLoginSuccessHandler formLoginSuccessHandler;

    private FormLoginFailHandler formLoginFailHandler;

    private FormAuthenticationProvider formAuthenticationProvider;

    @Autowired
    public void setFormAuthenticationProvider(FormAuthenticationProvider formAuthenticationProvider) {
        this.formAuthenticationProvider = formAuthenticationProvider;
    }

    @Autowired
    public void setFormLoginSuccessHandler(FormLoginSuccessHandler formLoginSuccessHandler) {
        this.formLoginSuccessHandler = formLoginSuccessHandler;
    }

    @Autowired
    public void setFormLoginFailHandler(FormLoginFailHandler formLoginFailHandler) {
        this.formLoginFailHandler = formLoginFailHandler;
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(formAuthenticationProvider);
        http.
                csrf().disable()
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/user/**", "/view/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/view/login")
                        .loginProcessingUrl("/user/login")
                        .successHandler(formLoginSuccessHandler)
                        .failureHandler(formLoginFailHandler)
                        .defaultSuccessUrl("/view/index", true)
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/error")
                .requestMatchers("/favicon.ico")
                .requestMatchers("/css/**")
                .requestMatchers("/js/**")
                .requestMatchers("/fonts/**")
                .requestMatchers("/static/**")
                .requestMatchers("/resources/**")
                .requestMatchers("/webjars/**");
    }
}
