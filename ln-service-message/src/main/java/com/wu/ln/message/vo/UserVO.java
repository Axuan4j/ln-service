package com.wu.ln.message.vo;

import jakarta.validation.constraints.DecimalMax;

public class UserVO {

    @DecimalMax(value = "10.0")
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "value=" + value +
                '}';
    }
}
