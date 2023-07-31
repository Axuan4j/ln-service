package com.wu.ln.user.service;

import com.wu.ln.bo.R;
import com.wu.ln.user.entity.UserVO;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService {

    R<String> sayHello(@RequestParam String name);

    R<String> login(UserVO userVO);

}
