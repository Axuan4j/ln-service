package com.wu.ln.db;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("ln_private_letter")
public class LnPrivateLetter {

    @TableId(type = com.baomidou.mybatisplus.annotation.IdType.AUTO, value = "letter_id")
    private Long letterId;

    @TableField("user_id")
    private Long userId;

    @TableField("letter_type")
    private Integer letterType;

    @TableField("following_id")
    private Long followingId;

    @TableField("content")
    private String content;

    @TableField("create_time")
    private String createTime;

    public Long getLetterId() {
        return letterId;
    }

    public void setLetterId(Long letterId) {
        this.letterId = letterId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getLetterType() {
        return letterType;
    }

    public void setLetterType(Integer letterType) {
        this.letterType = letterType;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
