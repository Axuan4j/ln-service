package com.wu.ln.util;

import com.wu.ln.bo.R;

import java.util.Date;

public class CreateR {

    public static <T> R<T> createSuccessResult() {
        return new R<>(0, "api call success", null, new Date());
    }

    public static <T> R<T> createSuccessResult(T data) {
        return new R<>(0, "api call success", data, new Date());
    }

    public static <T> R<T> createSuccessResult(String msg, T data) {
        return new R<>(0, msg, data, new Date());
    }

    public static <T> R<T> createCustomResult(int code, String msg) {
        return new R<>(code, msg, null, new Date());
    }

    public static <T> R<T> createCustomResult(int code, String msg, T data) {
        return new R<>(code, msg, data, new Date());
    }
}
