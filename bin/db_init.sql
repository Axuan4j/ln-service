/*
  db_name: ln
 */
DROP TABLE IF EXISTS `ln_user_account`;
CREATE TABLE `ln_user_account`
(
    `user_id`         int(11)      NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `user_name`       varchar(255) NOT NULL COMMENT '用户名',
    `password`        varchar(255) NOT NULL COMMENT '密码',
    `safe_code`       varchar(255)          DEFAULT NULL COMMENT '安全码',
    `is_vip`          int(1)       NOT NULL DEFAULT '0' COMMENT '是否是vip 0:否 1:是',
    `email`           varchar(255)          DEFAULT NULL COMMENT '邮箱',
    `phone`           varchar(255)          DEFAULT NULL COMMENT '手机号',
    `status`          int(11)      NOT NULL DEFAULT '0' COMMENT '状态0:正常 1:禁用',
    `create_time`     datetime              DEFAULT NULL COMMENT '创建时间',
    `update_pwd_time` datetime              DEFAULT NULL COMMENT '上次更新密码的时间',
    `last_login_time` datetime              DEFAULT NULL COMMENT '上次登录时间',
    PRIMARY KEY (`user_id`),
    UNIQUE `user_name` (`user_name`),
    UNIQUE `email` (`email`),
    INDEX `create_time` (`create_time`)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户账号表';


DROP TABLE IF EXISTS `ln_user_info`;
CREATE TABLE `ln_user_info`
(
    `user_id`     int(11)  NOT NULL COMMENT '用户id',
    `experience`  int(11)  NOT NULL DEFAULT '0' COMMENT '经验值',
    `level`       int(11)  NOT NULL DEFAULT '0' COMMENT '等级',
    `signature`   varchar(255)      DEFAULT NULL DEFAULT '' COMMENT '个性签名',
    `avatar`      varchar(255)      DEFAULT NULL DEFAULT '' COMMENT '头像',
    `region`      varchar(255)      DEFAULT NULL DEFAULT '' COMMENT '地区',
    `sex`         int(1)   NOT NULL DEFAULT '0' COMMENT '性别 0:未知 1:男 2:女',
    `birthday`    date              DEFAULT NULL DEFAULT '1970-01-01' COMMENT '生日',
    `qq`          varchar(255)      DEFAULT NULL DEFAULT '' COMMENT 'qq',
    `we_chat`     varchar(255)      DEFAULT NULL DEFAULT '' COMMENT '微信',
    `weibo`       varchar(255)      DEFAULT NULL DEFAULT '' COMMENT '微博',
    `douying`     varchar(255)      DEFAULT NULL DEFAULT '' COMMENT '抖音',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime          DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`user_id`)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户服务信息表';

DROP TABLE IF EXISTS `in_posts_group`;
Create Table `in_posts_group`
(
    `group_id`    int(11)      NOT NULL AUTO_INCREMENT COMMENT '板块id',
    `group_name`  varchar(255) NOT NULL COMMENT '板块名称',
    `group_icon`  varchar(255) NOT NULL COMMENT '板块图标',
    `description` varchar(255)          DEFAULT NULL COMMENT '板块描述',
    `status`      int(1)       NOT NULL DEFAULT '0' COMMENT '状态 0:正常 1:禁用',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`group_id`)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文章分组表';

DROP TABLE IF EXISTS `in_posts_info`;
Create Table `in_posts_info`
(
    `posts_id`    bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '文章id',
    `group_id`    int(11)      NOT NULL DEFAULT '0' COMMENT '板块id',
    `user_id`     int(11)      NOT NULL DEFAULT '0' COMMENT '用户id',
    `title`       varchar(255) NOT NULL COMMENT '标题',
    `content`     text         NOT NULL COMMENT '内容',
    `label`       varchar(255) NOT NULL COMMENT '标签',
    `status`      int(1)       NOT NULL DEFAULT '0' COMMENT '状态 0:正常 1:禁用',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime              DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`posts_id`),
    INDEX `group_id` (`group_id`),
    INDEX `user_id` (`user_id`),
    INDEX `label` (`label`),
    INDEX `create_time` (`create_time`)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文章信息表';

DROP TABLE IF EXISTS `in_posts_comment`;
Create Table `in_posts_comment`
(
    `comment_id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论id',
    `posts_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '文章id',
    `user_id`     int(11)    NOT NULL DEFAULT '0' COMMENT '用户id',
    `content`     text       NOT NULL COMMENT '内容',
    `reply_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '回复id',
    `create_time` datetime   NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`comment_id`),
    INDEX `resource_id` (`posts_id`),
    INDEX `user_id` (`user_id`),
    INDEX `create_time` (`create_time`)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文章评论表';

DROP TABLE IF EXISTS `in_posts_like`;
CREATE Table `ln_posts_like`
(
    `like_id`     bigint(20) NOT NULL AUTO_INCREMENT COMMENT '点赞id',
    `posts_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '文章id',
    `user_id`     int(11)    NOT NULL DEFAULT '0' COMMENT '用户id',
    `create_time` datetime   NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`like_id`),
    INDEX `resource_id` (`posts_id`)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文章点赞表';

DROP TABLE IF EXISTS `ln_follow`;
CREATE Table `ln_follows`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关注id',
    `user_id`      int(11)    NOT NULL DEFAULT '0' COMMENT '用户id',
    `following_id` int(11)    NOT NULL DEFAULT '0' COMMENT '关注用户id',
    `create_time`  datetime   NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    INDEX `user_id` (`user_id`),
    INDEX `follow_id` (`following_id`)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='用户关注表';

DROP TABLE IF EXISTS `ln_private_letter`;
CREATE Table `ln_private_letter`
(
    `letter_id`    bigint(20) NOT NULL AUTO_INCREMENT COMMENT '私信id',
    `user_id`      int(11)    NOT NULL DEFAULT '0' COMMENT '用户id',
    `letter_type`  int(1)     NOT NULL DEFAULT '0' COMMENT '私信类型 0:用户私信 1:系统私信',
    `following_id` int(11)    NOT NULL DEFAULT '0' COMMENT '关注用户id',
    `content`      text       NOT NULL COMMENT '内容',
    `create_time`  datetime   NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`letter_id`),
    INDEX `user_id` (`user_id`),
    INDEX `follow_id` (`following_id`)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='私信表';

DROP TABLE IF EXISTS `ln_sensitive_words`;
CREATE Table `ln_sensitive_words`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '敏感词id',
    `word`        varchar(255) NOT NULL COMMENT '敏感词',
    `status`      int(1)       NOT NULL DEFAULT '0' COMMENT '状态 0:正常 1:禁用',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE `word` (`word`)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='敏感词表';

DROP TABLE IF EXISTS `ln_posts_report`;
CREATE Table `ln_posts_report`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '举报id',
    `user_id`     int(11)    NOT NULL DEFAULT '0' COMMENT '用户id',
    `posts_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '文章id',
    `content`     text       NOT NULL COMMENT '内容',
    `create_time` datetime   NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    INDEX `user_id` (`user_id`),
    INDEX `posts_id` (`posts_id`)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='举报表';

DROP TABLE IF EXISTS `ln_posts_collection`;
CREATE Table `ln_posts_collection`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '收藏id',
    `user_id`     int(11)    NOT NULL DEFAULT '0' COMMENT '用户id',
    `posts_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '文章id',
    `create_time` datetime   NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    INDEX `user_id` (`user_id`),
    INDEX `posts_id` (`posts_id`)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文章收藏表';

DROP TABLE IF EXISTS `ln_posts_history`;
CREATE Table `ln_posts_history`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '历史记录id',
    `user_id`     int(11)    NOT NULL DEFAULT '0' COMMENT '用户id',
    `posts_id`    bigint(20) NOT NULL DEFAULT '0' COMMENT '文章id',
    `create_time` datetime   NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    INDEX `user_id` (`user_id`),
    INDEX `posts_id` (`posts_id`)
) DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='文章历史记录表';

DROP TABLE IF EXISTS `ln_admin_user`;
CREATE Table `ln_admin_user`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `user_name`   varchar(255) NOT NULL COMMENT '用户名',
    `password`    varchar(255) NOT NULL COMMENT '密码',
    `encrypt`     varchar(255) NOT NULL COMMENT '加密串',
    `real_name`   varchar(255) NOT NULL COMMENT '真实姓名',
    `status`      int(1)       NOT NULL DEFAULT '0' COMMENT '状态 0:正常 1:禁用',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE `user_name` (`user_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='管理员用户表';

DROP TABLE IF EXISTS `ln_admin_role`;
CREATE Table `ln_admin_role`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `role_name`   varchar(255) NOT NULL COMMENT '角色名称',
    `status`      int(1)       NOT NULL DEFAULT '0' COMMENT '状态 0:正常 1:禁用',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE `role_name` (`role_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='管理员角色表';

DROP TABLE IF EXISTS `ln_admin_role_user`;
CREATE Table `ln_admin_role_user`
(
    `id`          int(11)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `role_id`     int(11)  NOT NULL COMMENT '角色id',
    `user_id`     int(11)  NOT NULL COMMENT '用户id',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE `role_id` (`role_id`, `user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='管理员角色用户关联表';

DROP TABLE IF EXISTS `ln_admin_role_menu`;
CREATE Table `ln_admin_role_menu`
(
    `id`          int(11)  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `role_id`     int(11)  NOT NULL COMMENT '角色id',
    `menu_id`     int(11)  NOT NULL COMMENT '菜单id',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE `role_id` (`role_id`, `menu_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='管理员角色菜单关联表';

DROP TABLE IF EXISTS `ln_admin_menu`;
CREATE Table `ln_admin_menu`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '菜单id',
    `parent_id`   int(11)      NOT NULL DEFAULT '0' COMMENT '父级id',
    `menu_name`   varchar(255) NOT NULL COMMENT '菜单名称',
    `menu_url`    varchar(255) NOT NULL COMMENT '菜单url',
    `menu_icon`   varchar(255) NOT NULL COMMENT '菜单图标',
    `menu_sort`   int(11)      NOT NULL DEFAULT '0' COMMENT '菜单排序',
    `status`      int(1)       NOT NULL DEFAULT '0' COMMENT '状态 0:正常 1:禁用',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='管理员菜单表';

DROP TABLE IF EXISTS `ln_admin_log`;
CREATE Table `ln_admin_log`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '日志id',
    `user_id`     int(11)      NOT NULL COMMENT '用户id',
    `user_name`   varchar(255) NOT NULL COMMENT '用户名',
    `log_type`    int(1)       NOT NULL DEFAULT '0' COMMENT '日志类型 0:登录日志 1:操作日志',
    `log_content` text         NOT NULL COMMENT '日志内容',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='管理员日志表';

DROP TABLE IF EXISTS `ln_admin_login_log`;
CREATE Table `ln_admin_login_log`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '日志id',
    `user_id`     int(11)      NOT NULL COMMENT '用户id',
    `user_name`   varchar(255) NOT NULL COMMENT '用户名',
    `login_ip`    varchar(255) NOT NULL COMMENT '登录ip',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='管理员登录日志表';

DROP TABLE IF EXISTS `ln_announcement`;
CREATE TABLE `ln_announcement`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '公告id',
    `title`       varchar(255) NOT NULL COMMENT '标题',
    `content`     text         NOT NULL COMMENT '内容',
    `status`      int(1)       NOT NULL DEFAULT '0' COMMENT '状态 0:正常 1:禁用',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='公告表';

DROP TABLE IF EXISTS `ln_banner`;
CREATE TABLE `ln_banner`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '轮播图id',
    `title`       varchar(255) NOT NULL COMMENT '标题',
    `image`       varchar(255) NOT NULL COMMENT '图片',
    `url`         varchar(255) NOT NULL COMMENT '跳转url',
    `status`      int(1)       NOT NULL DEFAULT '0' COMMENT '状态 0:正常 1:禁用',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='轮播图表';

DROP TABLE IF EXISTS `ln_feedback`;
CREATE TABLE `ln_feedback`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '反馈id',
    `user_id`     int(11)    NOT NULL DEFAULT '0' COMMENT '用户id',
    `content`     text       NOT NULL COMMENT '内容',
    `create_time` datetime   NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    INDEX `user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='反馈表';

INSERT INTO `ln_admin_user` (`user_name`, `password`, `encrypt`, `real_name`, `status`, `create_time`)
VALUES ('admin', '64B9799343E5AEAE82B8FA77145FAC98', '121380', '超级管理员', 0, '2020-01-01 00:00:00');
INSERT INTO `ln_admin_role` (`id`, `role_name`, `status`, `create_time`)
VALUES (1, '超级管理员', 0, '2020-01-01 00:00:00');

CREATE TABLE oauth2_authorization_consent
(
    registered_client_id varchar(100)  NOT NULL,
    principal_name       varchar(200)  NOT NULL,
    authorities          varchar(1000) NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
);

/*
IMPORTANT:
    If using PostgreSQL, update ALL columns defined with 'blob' to 'text',
    as PostgreSQL does not support the 'blob' data type.
    Mysql 5.7+ timestamp DEFAULT NULL => timestamp NULL
*/
CREATE TABLE oauth2_authorization
(
    id                            varchar(100) NOT NULL,
    registered_client_id          varchar(100) NOT NULL,
    principal_name                varchar(200) NOT NULL,
    authorization_grant_type      varchar(100) NOT NULL,
    authorized_scopes             varchar(1000) DEFAULT NULL,
    attributes                    blob          DEFAULT NULL,
    state                         varchar(500)  DEFAULT NULL,
    authorization_code_value      blob          DEFAULT NULL,
    authorization_code_issued_at  timestamp    NULL,
    authorization_code_expires_at timestamp    NULL,
    authorization_code_metadata   blob          DEFAULT NULL,
    access_token_value            blob          DEFAULT NULL,
    access_token_issued_at        timestamp    NULL,
    access_token_expires_at       timestamp    NULL,
    access_token_metadata         blob          DEFAULT NULL,
    access_token_type             varchar(100)  DEFAULT NULL,
    access_token_scopes           varchar(1000) DEFAULT NULL,
    oidc_id_token_value           blob          DEFAULT NULL,
    oidc_id_token_issued_at       timestamp    NULL,
    oidc_id_token_expires_at      timestamp    NULL,
    oidc_id_token_metadata        blob          DEFAULT NULL,
    refresh_token_value           blob          DEFAULT NULL,
    refresh_token_issued_at       timestamp    NULL,
    refresh_token_expires_at      timestamp    NULL,
    refresh_token_metadata        blob          DEFAULT NULL,
    user_code_value               blob          DEFAULT NULL,
    user_code_issued_at           timestamp    NULL,
    user_code_expires_at          timestamp    NULL,
    user_code_metadata            blob          DEFAULT NULL,
    device_code_value             blob          DEFAULT NULL,
    device_code_issued_at         timestamp    NULL,
    device_code_expires_at        timestamp    NULL,
    device_code_metadata          blob          DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE oauth2_registered_client
(
    id                            varchar(100)                            NOT NULL,
    client_id                     varchar(100)                            NOT NULL,
    client_id_issued_at           timestamp     DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret                 varchar(200)  DEFAULT NULL,
    client_secret_expires_at      timestamp     DEFAULT CURRENT_TIMESTAMP,
    client_name                   varchar(200)                            NOT NULL,
    client_authentication_methods varchar(1000)                           NOT NULL,
    authorization_grant_types     varchar(1000)                           NOT NULL,
    redirect_uris                 varchar(1000) DEFAULT NULL,
    scopes                        varchar(1000)                           NOT NULL,
    client_settings               varchar(2000)                           NOT NULL,
    token_settings                varchar(2000)                           NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE oauth2_registered_client
    ADD `post_logout_redirect_uris` varchar(1000) DEFAULT NULL;

INSERT INTO oauth2_registered_client (id, client_id, client_id_issued_at, client_secret, client_secret_expires_at,
                                      client_name, client_authentication_methods, authorization_grant_types,
                                      redirect_uris, scopes, client_settings, token_settings, post_logout_redirect_uris)
VALUES ('64e8ba02-5bd1-4212-a33a-9bb9b13e3e7d', 'test-client', '2023-07-23 14:25:48', '{noop}test123456',
        '2024-07-23 14:25:47', '64e8ba02-5bd1-4212-a33a-9bb9b13e3e7d', 'client_secret_basic',
        'refresh_token,authorization_code', 'https://www.baidu.com', 'openid,profile,email',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}',
        '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",1800.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",864000.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}',
        '');

ALTER TABLE `oauth2_registered_client` CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;