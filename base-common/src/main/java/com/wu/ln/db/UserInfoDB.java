package com.wu.ln.db;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("ln_user_info")
public class UserInfoDB {

    @TableId(type = IdType.ASSIGN_ID, value = "user_id")
    private long userId;

    @TableField("experience")
    private long experience;

    @TableField("level")
    private long level;

    @TableField("signature")
    private String signature;

    @TableField("avatar")
    private String avatar;

    @TableField("region")
    private String region;

    @TableField("sex")
    private Integer sex;

    @TableField("birthday")
    private String birthday;

    @TableField("qq")
    private String qq;

    @TableField("we_chat")
    private String weChat;

    @TableField("weibo")
    private String weibo;

    @TableField("douying")
    private String douying;

    @TableField("create_time")
    private String createTime;

    @TableField("update_time")
    private String updateTime;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public long getExperience() {
        return experience;
    }

    public void setExperience(long experience) {
        this.experience = experience;
    }


    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }


    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public Integer getSex() {
        return sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }


    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }


    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }


    public String getDouying() {
        return douying;
    }

    public void setDouying(String douying) {
        this.douying = douying;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
