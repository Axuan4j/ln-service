package com.wu.ln.authorization.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class Oauth2ClientVO {

    private String clientId;

    @NotEmpty(message = "clientSecret不能为空")
    @Pattern(regexp = "^[a-zA-Z]\\w{5,10}$", message = "clientSecret必须是以字母开头，长度在6~11之间，只能包含字符、数字和下划线")
    private String clientSecret;

    @NotEmpty(message = "redirectUri不能为空")
    @Pattern(regexp = "^http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?$", message = "redirectUri格式不正确")
    private String redirectUri;

    @NotEmpty(message = "scope不能为空")
    private String scope;

    @NotEmpty(message = "clientName不能为空")
    private String clientName;

    public Integer expireToken;

    @NotEmpty(message = "grantTypes不能为空")
    public String grantTypes;

    public String getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(String grantTypes) {
        this.grantTypes = grantTypes;
    }

    public Integer getExpireToken() {
        return expireToken;
    }

    public void setExpireToken(Integer expireToken) {
        this.expireToken = expireToken;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "Oauth2ClientVO{" +
                "clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                ", scope='" + scope + '\'' +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
