package com.wu.ln.news.api;


import com.wu.ln.bo.R;
import com.wu.ln.news.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userService")
public class UserServiceApi {

    private final UserService userService;

    public UserServiceApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sayHello")
    public R<String> sayHello(String name) {
        return userService.sayHello(name);
    }
}
