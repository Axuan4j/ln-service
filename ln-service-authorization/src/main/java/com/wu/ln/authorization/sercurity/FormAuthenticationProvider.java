package com.wu.ln.authorization.sercurity;

import cn.hutool.crypto.digest.DigestUtil;
import com.wu.ln.authorization.entity.AppUserDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class FormAuthenticationProvider implements AuthenticationProvider {


    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    public FormAuthenticationProvider(PasswordEncoder passwordEncoder, @Qualifier("authorizedUserService") UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    /**
     * 认证逻辑
     *
     * @param authentication  认证信息
     * @return 认证信息
     * @throws AuthenticationException 认证异常
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        // 查询数据库中用户信息
        AppUserDetail userDetails = (AppUserDetail) userDetailsService.loadUserByUsername(authenticationToken.getName());
        if (!userDetails.isEnabled()) {
            throw new BadCredentialsException("用户已被禁用");
        }
        String hexPassword = DigestUtil.md5Hex(authentication.getCredentials().toString() + userDetails.getSafeCode());
        // 密码验证
        if (!passwordEncoder.matches(hexPassword, userDetails.getPassword())) {
            throw new BadCredentialsException("用户名密码不正确");
        }
        // 认证通过，返回认证信息
        return new UsernamePasswordAuthenticationToken(userDetails, hexPassword, userDetails.getAuthorities());

    }

    /**
     * 验证授权token类型
     *
     * @param authentication token类型
     * @return 是否支持
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}