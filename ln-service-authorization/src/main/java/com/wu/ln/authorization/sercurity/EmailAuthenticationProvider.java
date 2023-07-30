package com.wu.ln.authorization.sercurity;

import cn.hutool.core.util.StrUtil;
import com.wu.ln.authorization.entity.AppUserDetail;
import com.wu.ln.authorization.service.AllUserDetailService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class EmailAuthenticationProvider implements AuthenticationProvider {


    private final AllUserDetailService userDetailsService;

    private final RedisTemplate<String, String> redisTemplate;

    public EmailAuthenticationProvider(@Qualifier("authorizedUserService") AllUserDetailService userDetailsService, RedisTemplate<String, String> redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 认证逻辑
     *
     * @param authentication 认证信息
     * @return 认证信息
     * @throws AuthenticationException 认证异常
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        EmailAuthenticationToken authenticationToken = (EmailAuthenticationToken) authentication;
        Object principal = authenticationToken.getPrincipal();
        if (StrUtil.isBlankIfStr(principal)) {
            throw new BadCredentialsException("邮箱不能为空");
        }
        Object credentials = authenticationToken.getCredentials();
        if (StrUtil.isBlankIfStr(credentials)) {
            throw new BadCredentialsException("邮箱验证码不能为空");
        }
        String email = principal.toString();
        // 查询数据库中用户信息
        AppUserDetail userDetails = userDetailsService.loadUserByEmail(email);
        if (!userDetails.isEnabled()) {
            throw new BadCredentialsException("用户已被禁用");
        }
        String mailKey = "email:code:" + email;
        String redisCode = redisTemplate.opsForValue().get(mailKey);
        // 邮箱验证码验证
        if (!credentials.toString().equals(redisCode)) {
            throw new BadCredentialsException("邮箱验证码不正确");
        }
        // 认证通过，返回认证信息
        return createEmailAuthenticationToken(authentication, userDetails);
    }

    public EmailAuthenticationToken createEmailAuthenticationToken(Authentication authentication, UserDetails userDetails) {
        EmailAuthenticationToken emailAuthenticationToken = new EmailAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        emailAuthenticationToken.setDetails(authentication.getDetails());
        return emailAuthenticationToken;
    }

    /**
     * 验证授权token类型
     *
     * @param authentication token类型
     * @return 是否支持
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return EmailAuthenticationToken.class.isAssignableFrom(authentication);
    }
}