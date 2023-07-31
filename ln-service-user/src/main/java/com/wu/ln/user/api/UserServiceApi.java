package com.wu.ln.user.api;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.wu.ln.bo.R;
import com.wu.ln.user.entity.UserVO;
import com.wu.ln.user.service.UserService;
import com.wu.ln.util.CreateR;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorization")
public class UserServiceApi {

    private final UserService userService;

    public UserServiceApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @SentinelResource(value = "login", fallback = "loginFallback")
    public R<String> login(@Validated UserVO userVO) {
        return userService.login(userVO);
    }

    public R<String> loginFallback(UserVO userVO) {
        return CreateR.createSuccessResult("loginFallback");
    }
}
