package com.wu.ln.authorization.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.wu.ln.authorization.entity.AppUserDetail;

@JsonDeserialize(builder = AppUserDetail.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public abstract class UserDetailMixin {

    @JsonCreator
    public UserDetailMixin() {
    }
}
