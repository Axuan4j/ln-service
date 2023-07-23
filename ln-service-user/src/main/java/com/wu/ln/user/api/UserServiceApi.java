package com.wu.ln.user.api;


import com.wu.ln.bo.R;
import com.wu.ln.user.entity.UserVO;
import com.wu.ln.user.service.UserService;
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
    public R<String> login(@Validated UserVO userVO) {
        return userService.login(userVO);
    }
}
