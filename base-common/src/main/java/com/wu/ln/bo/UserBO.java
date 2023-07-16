package com.wu.ln.bo;

import java.io.Serial;
import java.io.Serializable;

public class UserBO implements Serializable {

    @Serial
    private static final long serialVersionUID = 10001L;

    private String username;

    private String password;

    public UserBO(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
        return "UserBO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
