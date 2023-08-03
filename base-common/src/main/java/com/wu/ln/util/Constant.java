package com.wu.ln.util;

public class Constant {

    public static final String LOCKED_REDIS_KEY = "user:locked:";

    public static final Integer ACCOUNT_LOGIN_FAIL_MAX_COUNT = 5;

    public static final Integer ACCOUNT_LOCKED_MINUTES = 5;

    public static final String ACCESS_TOKEN_REDIS_KEY = "access_token:user:";

    public static final long ACCESS_TOKEN_EXPIRE_MINUTES = 30;
}
