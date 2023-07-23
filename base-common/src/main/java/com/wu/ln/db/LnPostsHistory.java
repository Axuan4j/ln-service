package com.wu.ln.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("ln_posts_history")
public class LnPostsHistory {

    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO, value = "id")
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("posts_id")
    private Long postsId;

    @TableField("create_time")
    private String createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public Long getPostsId() {
        return postsId;
    }

    public void setPostsId(Long postsId) {
        this.postsId = postsId;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
