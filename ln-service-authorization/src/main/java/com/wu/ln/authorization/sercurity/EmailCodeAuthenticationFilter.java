/*
 * Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wu.ln.authorization.sercurity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

public class EmailCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_EMAIL_ADDRESS_KEY = "email";

    public static final String SPRING_SECURITY_FORM_EMAIL_CODE_KEY = "code";

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login/email", "POST");

    private String emailAddressParameter = SPRING_SECURITY_FORM_EMAIL_ADDRESS_KEY;

    private String emailCodeParameter = SPRING_SECURITY_FORM_EMAIL_CODE_KEY;

    private boolean postOnly = true;

    public EmailCodeAuthenticationFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    public EmailCodeAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String emailAddress = obtainEmailAddress(request);
        emailAddress = (emailAddress != null) ? emailAddress.trim() : "";
        String emailCode = obtainEmailCode(request);
        emailCode = (emailCode != null) ? emailCode : "";
        EmailAuthenticationToken authRequest = EmailAuthenticationToken.unauthenticated(emailAddress, emailCode);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }


    @Nullable
    protected String obtainEmailAddress(HttpServletRequest request) {
        return request.getParameter(this.emailAddressParameter);
    }

    @Nullable
    protected String obtainEmailCode(HttpServletRequest request) {
        return request.getParameter(this.emailCodeParameter);
    }

    protected void setDetails(HttpServletRequest request, EmailAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }


    public void setEmailAddressParameter(String emailAddressParameter) {
        Assert.hasText(emailAddressParameter, "Email Address parameter must not be empty or null");
        this.emailAddressParameter = emailAddressParameter;
    }

    public void setEmailCodeParameter(String emailCodeParameter) {
        Assert.hasText(emailCodeParameter, "Email Code parameter must not be empty or null");
        this.emailCodeParameter = emailCodeParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getEmailCodeParameter() {
        return this.emailCodeParameter;
    }

    public final String getEmailAddressParameter() {
        return this.emailAddressParameter;
    }

}
