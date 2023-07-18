package com.wu.ln.exceptions;

public class AuthorizedException extends AbstractApplicationException {

    public AuthorizedException(String message, int code) {
        super(message, code);
    }
}
