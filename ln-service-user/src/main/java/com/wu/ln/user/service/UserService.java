package com.wu.ln.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wu.ln.db.UserAccountDB;
import com.wu.ln.user.entity.UserServiceDetail;

public interface UserService extends IService<UserAccountDB> {

    UserServiceDetail loadUserByUsername(String username);
}
