package com.wu.ln.user.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class UserVO {

    @NotEmpty(message = "用户名不能为空")
    @Length(min = 5, max = 7, message = "用户名长度必须在5-7之间")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Pattern(regexp = "^[a-zA-Z]\\w{5,17}$", message = "密码必须是以字母开头，长度在6~18之间，只能包含字符、数字和下划线")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
