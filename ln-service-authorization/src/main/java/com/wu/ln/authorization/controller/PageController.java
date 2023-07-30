package com.wu.ln.authorization.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.wu.ln.authorization.entity.Oauth2ClientVO;
import com.wu.ln.bo.R;
import com.wu.ln.util.CreateR;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Map;

@Controller
public class PageController {

    private RegisteredClientRepository registeredClientRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setRegisteredClientRepository(RegisteredClientRepository registeredClientRepository) {
        this.registeredClientRepository = registeredClientRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "new_login";
    }

    @GetMapping("/page/access-application")
    public String accessApplication() {
        return "access_approval";
    }

    @GetMapping("/page/email-login")
    public String emailLogin() {
        return "new_email";
    }

    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println(authentication);
        return "index";
    }

    @PostMapping("/register-client")
    public R<?> registerClient(@Validated @RequestBody Oauth2ClientVO clientVO) {
        // JWT（Json Web Token）的配置项：TTL、是否复用refreshToken等等
        TokenSettings tokenSettings = TokenSettings.builder()
                // 令牌存活时间：30分钟
                .accessTokenTimeToLive(Duration.ofDays(1))
                // 令牌可以刷新，重新获取
                .reuseRefreshTokens(true)
                // 刷新时间：30天（30天内当令牌过期时，可以用刷新令牌重新申请新令牌，不需要再认证）
                .refreshTokenTimeToLive(Duration.ofDays(clientVO.getExpireToken() == null ? 30 : clientVO.getExpireToken()))
                .build();
        // 客户端相关配置
        ClientSettings clientSettings = ClientSettings.builder()
                // 是否需要用户授权确认
                .requireAuthorizationConsent(true)
                .build();

        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
                // 客户端ID和密码
                .clientId(StringUtils.hasText(clientVO.getClientId()) ? clientVO.getClientId() : RandomUtil.randomString(10))
                .clientSecret(passwordEncoder.encode(clientVO.getClientSecret()))
                .clientIdIssuedAt(Instant.now())
                .clientSecretExpiresAt(null)
                // 授权方法
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                // 授权类型
                .authorizationGrantTypes(authorizationGrantTypes -> {
                    String[] grantTypeArray = clientVO.getGrantTypes().split(",");
                    for (String grantType : grantTypeArray) {
                        authorizationGrantTypes.add(new AuthorizationGrantType(grantType));
                    }
                })
                // 回调地址：授权服务器向当前客户端响应时调用下面地址, 不在此列的地址将被拒绝, 只能使用IP或域名，不能使用 localhost
                .redirectUri(clientVO.getRedirectUri())
                // 授权范围（当前客户端的授权范围）
                .scopes(scope -> {
                    // OIDC 支持
                    scope.add(OidcScopes.OPENID);
                    scope.add(OidcScopes.PROFILE);
                    scope.addAll(Arrays.asList(clientVO.getScope().split(",")));
                })
                // JWT（Json Web Token）配置项
                .tokenSettings(tokenSettings)
                // 客户端配置项
                .clientSettings(clientSettings)
                .build();
        registeredClientRepository.save(registeredClient);
        return CreateR.createSuccessResult("客户端注册成功", null);
    }

    @PostMapping("/error")
    @ResponseBody
    public String error(Map<String, Object> map) {
        System.out.println("error:" + map);
        return "error";
    }
}
