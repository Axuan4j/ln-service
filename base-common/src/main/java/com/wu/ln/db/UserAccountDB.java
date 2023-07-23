package com.wu.ln.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * CREATE TABLE `ln_user_account`
 * (
 * `user_id`         int(11)      NOT NULL AUTO_INCREMENT COMMENT '用户id',
 * `user_name`       varchar(255) NOT NULL COMMENT '用户名',
 * `password`        varchar(255) NOT NULL COMMENT '密码',
 * `safe_code`       varchar(255)          DEFAULT NULL COMMENT '安全码',
 * `is_vip`          int(1)       NOT NULL DEFAULT '0' COMMENT '是否是vip 0:否 1:是',
 * `email`           varchar(255)          DEFAULT NULL COMMENT '邮箱',
 * `phone`           varchar(255)          DEFAULT NULL COMMENT '手机号',
 * `status`          int(11)      NOT NULL DEFAULT '0' COMMENT '状态0:正常 1:禁用',
 * `create_time`     datetime              DEFAULT NULL COMMENT '创建时间',
 * `update_pwd_time` datetime              DEFAULT NULL COMMENT '上次更新密码的时间',
 * `last_login_time` datetime              DEFAULT NULL COMMENT '上次登录时间',
 * PRIMARY KEY (`user_id`),
 * UNIQUE `user_name` (`user_name`),
 * UNIQUE `email` (`email`),
 * INDEX `create_time` (`create_time`)
 * ) DEFAULT CHARSET = utf8mb4
 * COLLATE = utf8mb4_unicode_ci COMMENT ='用户账号表';
 */
@TableName("ln_user_account")
public class UserAccountDB {

    @TableId(type = IdType.AUTO, value = "user_id")
    private Long userId;

    @TableField("user_name")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("safe_code")
    private String safeCode;

    @TableField("is_vip")
    private Integer isVip;

    @TableField("email")
    private String email;

    @TableField("phone")
    private String phone;

    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private String createTime;

    @TableField("update_pwd_time")
    private String updatePwdTime;

    @TableField("last_login_time")
    private String lastLoginTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSafeCode() {
        return safeCode;
    }

    public void setSafeCode(String safeCode) {
        this.safeCode = safeCode;
    }

    public Integer getIsVip() {
        return isVip;
    }

    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdatePwdTime() {
        return updatePwdTime;
    }

    public void setUpdatePwdTime(String updatePwdTime) {
        this.updatePwdTime = updatePwdTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "UserAccountDB{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", safeCode='" + safeCode + '\'' +
                ", isVip=" + isVip +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                ", updatePwdTime='" + updatePwdTime + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                '}';
    }
}
