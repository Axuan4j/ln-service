package com.wu.ln.exceptions;

public class DateBaseException extends AbstractApplicationException {

    public DateBaseException(String message, int code) {
        super(message, code);
    }

    @Override
    public String getExceptionName() {
        return "数据库处理异常";
    }
}
