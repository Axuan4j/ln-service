package com.wu.ln.authorization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wu.ln.authorization.entity.UserVO;
import com.wu.ln.db.UserAccountDB;

public interface AccountUserService extends IService<UserAccountDB> {

    UserAccountDB getUserByUsername(String username);

    boolean checkUsername(String username);

    Integer register(UserVO userVO);

    UserAccountDB loadUserByEmail(String email);


}
