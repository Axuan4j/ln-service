package com.wu.ln.db;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("in_posts_comment")
public class InPostsComment {

    @TableId(type = IdType.AUTO, value = "comment_id")
    private Long commentId;

    @TableField("posts_id")
    private Long postsId;

    @TableField("user_id")
    private Long userId;

    @TableField("content")
    private String content;

    @TableField("reply_id")
    private Long replyId;

    @TableField("create_time")
    private String createTime;


    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }


    public Long getPostsId() {
        return postsId;
    }

    public void setPostsId(Long postsId) {
        this.postsId = postsId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
