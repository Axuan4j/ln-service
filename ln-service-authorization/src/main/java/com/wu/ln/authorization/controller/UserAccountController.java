package com.wu.ln.authorization.controller;

import com.wu.ln.authorization.entity.UserVO;
import com.wu.ln.authorization.service.AccountUserService;
import com.wu.ln.bo.R;
import com.wu.ln.exceptions.DateBaseException;
import com.wu.ln.exceptions.ParamsException;
import com.wu.ln.util.CreateR;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserAccountController {

    private final AccountUserService accountUserService;

    public UserAccountController(AccountUserService accountUserService) {
        this.accountUserService = accountUserService;
    }

    @PostMapping("/register-user")
    public R<?> register(@Validated @RequestBody UserVO userVO) {
        if (accountUserService.checkUsername(userVO.getUsername())) {
            throw new ParamsException("用户名已存在", 400);
        }
        Integer state = accountUserService.register(userVO);
        if (state == 0) {
            throw new DateBaseException("注册失败", 500);
        }
        return CreateR.createSuccessResult("用户注册成功", null);
    }



}
