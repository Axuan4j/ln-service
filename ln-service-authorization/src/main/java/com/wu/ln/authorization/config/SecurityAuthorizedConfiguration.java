package com.wu.ln.authorization.config;

import cn.hutool.core.lang.UUID;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.wu.ln.authorization.sercurity.EmailAuthenticationSecurityConfig;
import com.wu.ln.authorization.sercurity.FormAuthenticationFailHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityAuthorizedConfiguration {

    private static final String CUSTOM_CONSENT_PAGE_URI = "/oauth2/consent";

    private EmailAuthenticationSecurityConfig emailAuthenticationSecurityConfig;

    private FormAuthenticationFailHandler formAuthenticationFailHandler;

    @Autowired
    public void setFormAuthenticationFailHandler(FormAuthenticationFailHandler formAuthenticationFailHandler) {
        this.formAuthenticationFailHandler = formAuthenticationFailHandler;
    }

    @Autowired
    public void setEmailAuthenticationSecurityConfig(EmailAuthenticationSecurityConfig emailAuthenticationSecurityConfig) {
        this.emailAuthenticationSecurityConfig = emailAuthenticationSecurityConfig;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http
                .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .authorizationEndpoint(
                        authorizationEndpoint -> authorizationEndpoint
                                .consentPage(CUSTOM_CONSENT_PAGE_URI)) // 自定义授权页面
                .oidc(Customizer.withDefaults());    // Enable OpenID Connect 1.0
        http
                .exceptionHandling(
                        (exceptions) -> exceptions
                                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }

    @Bean
    @Order(1)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, PasswordAuthenticationProvider passwordAuthenticationProvider) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // 配置放行的请求
                        .requestMatchers("/api/**", "/login", "/login/**", "/css/**", "/page/**", "/error").permitAll()
                        // 其他任何请求都需要认证
                        .anyRequest().authenticated()
                )
                .csrf().disable()
                // 设置登录表单页面
                .formLogin(formLogin ->
                        formLogin.loginPage("/login")
                                .failureHandler(formAuthenticationFailHandler)
                )
                .apply(emailAuthenticationSecurityConfig);
        AuthenticationManagerBuilder sharedObject = http.getSharedObject(AuthenticationManagerBuilder.class);
        sharedObject.authenticationProvider(passwordAuthenticationProvider);
        return http.build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

}
