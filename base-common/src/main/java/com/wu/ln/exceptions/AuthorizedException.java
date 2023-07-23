package com.wu.ln.exceptions;

public class AuthorizedException extends AbstractApplicationException {

    public AuthorizedException(String message, int code) {
        super(message, code);
    }

    @Override
    public String getExceptionName() {
        return "用户认证异常";
    }
}
