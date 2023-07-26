package com.wu.ln.authorization.service;

import com.wu.ln.authorization.entity.AppUserDetail;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AllUserDetailService extends UserDetailsService {

    AppUserDetail loadUserByEmail(String email);

}
