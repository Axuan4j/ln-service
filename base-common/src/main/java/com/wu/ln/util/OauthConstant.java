package com.wu.ln.util;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;

import java.util.List;
import java.util.Map;

public class OauthConstant {

    public static final String SCOPE_USER_INFO = "user_info";

    public static final String SCOPE_USER_INFO_DESC = "读取用户基础信息";

    public static final String SCOPE_POSTS_READ = "posts_read";

    public static final String SCOPE_POSTS_READ_DESC = "读取我的文章信息";

    public static final String SCOPE_POSTS_DELETE = "posts_delete";

    public static final String SCOPE_POSTS_DELETE_DESC = "删除我的已发布文章";

    public static final String SCOPE_POSTS_PUBLISH = "posts_publish";

    public static final String SCOPE_POSTS_PUBLISH_DESC = "发布我的文章";

    /**
     * 授权模式（授权码模式）
     */
    public static final String AUTHORIZATION_CODE = "authorization_code";

    /**
     * 授权模式（客户端模式）
     */
    public static final String CLIENT_CREDENTIALS = "client_credentials";

    /**
     * 授权模式（设备码模式）
     */
    public static final String DEVICE_CODE = "urn:ietf:params:oauth:grant-type:device_code";

    /**
     * 授权模式（JWT模式）
     */
    public static final String JWT_BEARER = "urn:ietf:params:oauth:grant-type:jwt-bearer";

    /**
     * 授权模式（刷新令牌模式）
     */
    public static final String REFRESH_TOKEN = "refresh_token";

    public static final List<String> grantType = ListUtil.of(AUTHORIZATION_CODE, CLIENT_CREDENTIALS, DEVICE_CODE, JWT_BEARER, REFRESH_TOKEN);

    public static final Map<String, String> scopes = MapUtil
            .builder(SCOPE_USER_INFO, SCOPE_USER_INFO_DESC)
            .put(SCOPE_POSTS_READ, SCOPE_POSTS_READ_DESC)
            .put(SCOPE_POSTS_DELETE, SCOPE_POSTS_DELETE_DESC)
            .put(SCOPE_POSTS_PUBLISH, SCOPE_POSTS_PUBLISH_DESC)
            .build();
}
