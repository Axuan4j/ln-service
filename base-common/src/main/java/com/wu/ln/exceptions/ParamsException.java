package com.wu.ln.exceptions;

public class ParamsException extends AbstractApplicationException {

    public ParamsException(String message, int code) {
        super(message, code);
    }

    @Override
    public String getExceptionName() {
        return "参数验证异常";
    }
}
