package com.wu.ln.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("ln_admin_role_user")
public class LnAdminRoleUser {

    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO, value = "id")
    private Long id;

    @TableField("role_id")
    private Integer roleId;

    @TableField("user_id")
    private Integer userId;

    @TableField("create_time")
    private String createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
