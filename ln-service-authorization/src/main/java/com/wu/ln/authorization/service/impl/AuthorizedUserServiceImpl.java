package com.wu.ln.authorization.service.impl;

import com.wu.ln.authorization.entity.AppUserDetail;
import com.wu.ln.authorization.service.AccountUserService;
import com.wu.ln.authorization.service.AllUserDetailService;
import com.wu.ln.db.UserAccountDB;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("authorizedUserService")
public class AuthorizedUserServiceImpl implements AllUserDetailService {

    private final AccountUserService accountUserService;

    public AuthorizedUserServiceImpl(AccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    @Override
    public AppUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccountDB userAccount = accountUserService.getUserByUsername(username);
        if (userAccount == null) {
            throw new UsernameNotFoundException("用户名" + username + "不存在");
        }
        AppUserDetail appUserDetail = new AppUserDetail();
        appUserDetail.setUsername(userAccount.getUserName());
        appUserDetail.setPassword(userAccount.getPassword());
        appUserDetail.setStatus(userAccount.getStatus());
        appUserDetail.setSafeCode(userAccount.getSafeCode());
        return appUserDetail;
    }

    @Override
    public AppUserDetail loadUserByEmail(String email) {
        UserAccountDB userAccountDB = accountUserService.loadUserByEmail(email);
        if (userAccountDB == null) {
            throw new UsernameNotFoundException("邮箱" + email + "不存在");
        }
        AppUserDetail appUserDetail = new AppUserDetail();
        appUserDetail.setUsername(userAccountDB.getUserName());
        appUserDetail.setEmail(userAccountDB.getEmail());
        appUserDetail.setStatus(userAccountDB.getStatus());
        return appUserDetail;
    }
}
