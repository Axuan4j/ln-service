package com.wu.ln.exceptions;

public abstract class AbstractApplicationException extends RuntimeException {

    private final int code;

    private final String message;

    public AbstractApplicationException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
